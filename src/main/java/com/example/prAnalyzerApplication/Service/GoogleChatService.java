package com.example.prAnalyzerApplication.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GoogleChatService {

    @Value("${google.chat-webhook-url}")
    private String chatWebhookUrl;

    private  final WebClient.Builder webclient;


    public GoogleChatService(WebClient.Builder webclient) {
        this.webclient = webclient;
    }

    public void sendChatNotification(String repo,int prNumber,String summary){
        String message = String.format("Ai review is completed *\n*Repo :%s\n*PR #:*%s\n\n* Summary:*\n%s",repo,prNumber,summary);

        webclient.build()
                .post()
                .uri(chatWebhookUrl)
                .header("Content-Type", "application/json")
                .bodyValue(Map.of("text", message))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
