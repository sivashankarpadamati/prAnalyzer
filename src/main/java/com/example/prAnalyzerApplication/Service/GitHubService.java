package com.example.prAnalyzerApplication.Service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GitHubService {

    @Value("${github.token}")
    private String gitHubToken;

    @Value("${github.base-url:https://api.github.com}")
    private String gitHubBaseUrl;

    private final WebClient.Builder webclient;

    public GitHubService(WebClient.Builder webclient) {
        this.webclient = webclient;
    }

    public String gitPullRequestDiff(String repo,int prNumber){
            System.out.println("Url = " + "chnage1");
            String Url = gitHubBaseUrl + "/repos/" + repo + "/pulls/" + prNumber;
            return webclient.build().get().uri(Url).header("Authorization",gitHubToken)
                    .header("Accept","application/vnd.github.v3.diff").
                    retrieve().bodyToMono(String.class).block();
    }
}
