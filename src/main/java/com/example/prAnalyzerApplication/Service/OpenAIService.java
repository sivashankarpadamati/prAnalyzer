package com.example.prAnalyzerApplication.Service;

import com.example.prAnalyzerApplication.Model.OpenAIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

import static com.example.prAnalyzerApplication.Utils.Constants.PROMPT;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    @Value("${openai.api-key}")
    private String openaiApiKey;

    @Value("${openai.model:gpt-4}") // ✅ CORRECT
    private String model;

    private final WebClient.Builder webClient;

    public String generateReview(String diff) {
        System.out.print("openai changes1231416");
        String prompt = PROMPT + "\n\n" + diff;

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "You are an expert code reviewer."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        OpenAIResponse res = webClient.build().post().uri("https://api.openai.com/v1/chat/completions").header(
                        "Authorization", "Bearer " + openaiApiKey
                ).header("Content-Type", "application/json").bodyValue(requestBody).
                retrieve().bodyToMono(OpenAIResponse.class).block();
        return res.getChoices().get(0).getMessage().getContent();
    }
}
