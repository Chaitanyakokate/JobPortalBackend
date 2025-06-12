package com.jobportal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class QnAService {

    @Value("${gemini.api.url}") // Corrected @Value annotation
    private String geminiApiUrl;

    @Value("${gemini.api.key}") // Corrected @Value annotation
    private String geminiApiKey;

    private final WebClient webClient;

    public QnAService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", question)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //IMPORTANT: You will need to parse the response to get the text.
        //The response is a JSON String, and you need to extract the answer.
        //Use a JSON library (e.g., Jackson, Gson) to parse the JSON.

        return response; //This is currently returning a JSON string.
    }
}