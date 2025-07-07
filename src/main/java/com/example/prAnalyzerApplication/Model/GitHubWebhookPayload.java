package com.example.prAnalyzerApplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubWebhookPayload {
    private String action;
    @JsonProperty("pull_request")
    private PullRequest pullRequest;
    private Repository repository;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PullRequest {
        private int number;
        private String title;
        private User user;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Repository {
        @JsonProperty("full_name")
        private String fullName;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String login;
    }
}
