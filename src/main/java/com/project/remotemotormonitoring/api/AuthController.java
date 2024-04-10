package com.project.remotemotormonitoring.api;

import com.project.remotemotormonitoring.service.security.AuthService;
import com.project.remotemotormonitoring.service.security.dto.LoginRequest;
import com.project.remotemotormonitoring.service.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        return authService.login(loginRequest);
    }


}
