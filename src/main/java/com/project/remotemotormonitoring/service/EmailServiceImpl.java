package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.Response;
import com.project.remotemotormonitoring.service.exceptions.FailedToSendEmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    @Value("${email.from}")
    private String from;

    @Value("${email.to}")
    private String to;

    @Value("${email.fromName}") // Add this property in your application.yml
    private String fromName;

    private final JavaMailSender mailSender;
    @Override
    public ResponseEntity<Response> sendSimpleMessage(String text ) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from,fromName);
            helper.setTo(to);
            helper.setSubject("Remote Monitoring notification");
            helper.setText(text);
        } catch (MessagingException e) {
            throw new FailedToSendEmailException(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(mimeMessage);
        return ResponseEntity.ok(Response.builder()
                        .message("email sent successfully")
                .build());
    }
    }

