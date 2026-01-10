package com.enterprise.products.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;

@Configuration
public class AiGeminiConfig {

    @Bean
    public VertexAI vertexAI() throws IOException {
        return new VertexAI("gcp-adk-100270", "us-central1");
    }

    @Bean
    public GenerativeModel chatBisonModel(VertexAI vertexAI) {
        //return new GenerativeModel("gemini-1.5-flash", vertexAI);
    	return new GenerativeModel("text-bison-001", vertexAI);
    	
    }
}

