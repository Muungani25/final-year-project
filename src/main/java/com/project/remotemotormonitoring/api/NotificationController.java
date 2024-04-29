package com.project.remotemotormonitoring.api;

import com.project.remotemotormonitoring.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/email")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody String message){
        log.info("sending message :{}",message);
      return   emailService.sendSimpleMessage(message);
    }
}
