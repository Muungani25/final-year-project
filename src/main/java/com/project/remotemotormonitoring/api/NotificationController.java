package com.project.remotemotormonitoring.api;

import com.project.remotemotormonitoring.api.dto.NotificationRequest;
import com.project.remotemotormonitoring.api.dto.SmsRequest;
import com.project.remotemotormonitoring.api.dto.SmsResponse;
import com.project.remotemotormonitoring.service.EmailService;
import com.project.remotemotormonitoring.service.SMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final EmailService emailService;
    private final SMSService smsService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody NotificationRequest request){
        log.info("sending message :{}",request);
      return   emailService.sendSimpleMessage(request);
    }

    @PostMapping("/send_sms")
    public void sendSMS(@RequestBody String text){
        smsService.sendSms("",text);
    }

    @PostMapping("/sms")
    public void send(@RequestBody SmsRequest request){
         smsService.sendSmsWithClickatell(request);

    }
}
