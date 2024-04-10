package com.project.remotemotormonitoring.service.security;


import com.project.remotemotormonitoring.service.security.dto.LoginRequest;
import com.project.remotemotormonitoring.service.security.dto.LoginResponse;
import org.springframework.http.ResponseEntity;



public interface AuthService {
    ResponseEntity<LoginResponse> login(LoginRequest request);
}
