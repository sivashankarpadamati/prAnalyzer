package com.example.prAnalyzerApplication.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ReviewCommentService {

    @Value("$github.token")
    private String githubToken;

    @Value("${github.base-url:http://api.github.com}")
    private String gitHubBaseurl;
    private final WebClient.Builder webclient;

    public  ReviewCommentService(WebClient.Builder webclient){
        this.webclient = webclient;
    }

    public void postReview(String repo,int prNumber,String comment){
        String Url = gitHubBaseurl + "/repos/" + repo + "/issues/" + prNumber + "/comments";

        Map<String,String> requestBody = Map.of("body",comment);

        webclient.build().post().uri(gitHubBaseurl).bodyValue(requestBody).header("Authorization", "Bearer " + githubToken).
                header("Accept","application/vnd.github.v3+json").retrieve().toBodilessEntity().block();}
}
