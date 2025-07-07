package com.example.prAnalyzerApplication.Service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReviewMail(String to, String repo, String prNumber, String reviewContent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(to);
        message.setSubject("AI Code Review - PR #" + prNumber + " in " + repo);
        message.setText("Hello,\n\nHere is the AI-generated review for PR #" + prNumber + " in repo " + repo + ":\n\n" + reviewContent);
        mailSender.send(message);
    }

}
