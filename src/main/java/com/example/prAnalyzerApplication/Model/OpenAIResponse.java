package com.example.prAnalyzerApplication.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

import java.awt.*;
import java.util.List;

@Data
public class OpenAIResponse {
    private List<Choice> choices;


    @Data
    public static class Choice {
        private int index;
        private Message message;
        private String finish_reason;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}
