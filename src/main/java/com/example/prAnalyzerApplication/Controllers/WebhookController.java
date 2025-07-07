package com.example.prAnalyzerApplication.Controllers;


import com.example.prAnalyzerApplication.Model.GitHubWebhookPayload;
import com.example.prAnalyzerApplication.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.prAnalyzerApplication.Utils.Constants.PULL_REQUEST;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {
    private final GitHubService gitHubService;
    private final OpenAIService openAIService;
    private final ReviewCommentService reviewCommentService;
    private final GoogleChatService googleChatService;
    private final EmailService emailService;


    @PostMapping("/github")
    public ResponseEntity<String> handleWebhook(@RequestBody GitHubWebhookPayload gitHubWebhookPayload,
                                                @RequestHeader("x-GitHub-Event") String eventType){

        if(PULL_REQUEST.equalsIgnoreCase(eventType) &&
                List.of("opened","synchronize","reopened").contains(gitHubWebhookPayload.getAction())){
            String repo  = gitHubWebhookPayload.getRepository().getFullName(); // Get repo
            int prNumber = gitHubWebhookPayload.getPullRequest().getNumber(); // Get pr number
            String diff = gitHubService.gitPullRequestDiff(repo, prNumber);
            String review = openAIService.generateReview(diff);
            reviewCommentService.postReview(repo,prNumber,review);
           // googleChatService.sendChatNotification(repo,prNumber,"");
            emailService.sendReviewMail("sivasahankarpadamati@gmail.com",repo,String.valueOf(prNumber),review);
        }
            return ResponseEntity.ok("success");
    }
}
