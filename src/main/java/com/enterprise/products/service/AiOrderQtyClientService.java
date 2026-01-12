package com.enterprise.products.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.enterprise.products.utils.ILConstants;

@Service
public class AiOrderQtyClientService {

    private final WebClient webClient;
    //This is order qty client service 
    public AiOrderQtyClientService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl(ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL)
                .build();
    }

    public String getOrderById(Long orderId) {
        return webClient.get()
                .uri(ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL+"/orderid/{orderId}", orderId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}


