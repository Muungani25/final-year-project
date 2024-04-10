package com.project.remotemotormonitoring.service.security;


import com.project.remotemotormonitoring.service.security.dto.LoginRequest;
import com.project.remotemotormonitoring.service.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;



@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService{
    private final RestTemplate restTemplate;

    @Value("${keycloak.client-id}")
    private String client_id;
    @Value("${keycloak.client-secret}")
    private String client_secret;
    @Value("${keycloak.grant_type}")
    private String grant_type;

    @Value("${keycloak.token-uri}")
    private String token_uri;
    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("client_id",client_id);
        map.add("client_secret",client_secret);
        map.add("grant_type",grant_type);
        map.add("username",request.getUsername());
        map.add("password",request.getPassword());

        HttpEntity<LinkedMultiValueMap<String,String>> httpEntity = new HttpEntity<>(map,headers);

        log.info("request : {}",httpEntity);
       ResponseEntity<LoginResponse> response= restTemplate.postForEntity(token_uri,httpEntity, LoginResponse.class);
       return ResponseEntity.ok(response.getBody());
    }
}
