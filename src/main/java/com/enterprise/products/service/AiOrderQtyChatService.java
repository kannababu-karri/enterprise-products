package com.enterprise.products.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.enterprise.products.utils.ILConstants;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;

@Service
public class AiOrderQtyChatService {

    private final AiOrderQtyClientService aiOrderQtyClientService;
    private final GenerativeModel model;

    public AiOrderQtyChatService(
            AiOrderQtyClientService aiOrderQtyClientService,
            GenerativeModel model) {

        this.aiOrderQtyClientService = aiOrderQtyClientService;
        this.model = model;
    }

    public String chat(String userMessage) throws Exception {

        Long orderId = extractOrderId(userMessage);

        String orderJson = aiOrderQtyClientService.getOrderById(orderId);

        String prompt = """
        You are an enterprise order assistant.

        Order data (JSON):
        %s

        User question:
        %s

        Answer clearly and professionally.
        """.formatted(orderJson, userMessage);

        GenerateContentResponse response = model.generateContent(prompt);

        return response.getCandidates(0)
                .getContent()
                .getParts(0)
                .getText();
    }

    private Long extractOrderId(String text) {
        return Long.parseLong(text.replaceAll("\\D+", ""));
    }
}


