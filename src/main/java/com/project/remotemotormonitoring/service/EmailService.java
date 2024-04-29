package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.Response;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface EmailService {

     ResponseEntity<Response> sendSimpleMessage(String message);
}
