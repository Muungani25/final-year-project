package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.NotificationRequest;
import com.project.remotemotormonitoring.api.dto.Response;
import com.project.remotemotormonitoring.api.dto.SmsRequest;
import com.project.remotemotormonitoring.domain.VariableType;
import com.project.remotemotormonitoring.service.exceptions.FailedToSendEmailException;
import com.project.remotemotormonitoring.service.exceptions.ResourceNotFoundException;
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
    private final SMSService smsService;
    @Override
    public ResponseEntity<Response> sendSimpleMessage(NotificationRequest request ) {
        var text =getMessage(VariableType.valueOf(request.getVariable()),request.getValue(),request.getPump());
//        var smsRequest=SmsRequest.builder()
//                .content(text)
//                .to("0778873016")
//                .build();
//        smsService.sendSmsWithClickatell(smsRequest);\
        smsService.sendSms("",text);
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

    private String getMessage(VariableType variableType,String value,String pump){
        var doubleValue=Double.valueOf(value);
        String formattedValue = String.format("%.2f", doubleValue);
        return switch (variableType) {
            case TEMP -> "Temperature for "+pump+" is " + formattedValue + "°C which is above the set threshold please attend";
            case VIB -> "Vibration frequency for "+pump+" is " + formattedValue + " Hz which is above the set threshold please attend";
            case CURRENT -> "Current for "+pump+"is " + formattedValue + "A which is above the set threshold please attend";
            default -> throw new ResourceNotFoundException("variable type does not exist");
        };
    }
    }

