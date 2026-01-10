package com.enterprise.products.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.products.service.AiOrderQtyChatService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class AiChatController {

    private final AiOrderQtyChatService aiOrderQtyChatService;

    public AiChatController(AiOrderQtyChatService aiOrderQtyChatService) {
        this.aiOrderQtyChatService = aiOrderQtyChatService;
    }

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> request) throws Exception {
        String userMessage = request.get("message");
        String reply = aiOrderQtyChatService.chat(userMessage);
        return Map.of("reply", reply);
    }
}

