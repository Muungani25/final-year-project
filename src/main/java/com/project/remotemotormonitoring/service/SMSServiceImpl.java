package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.SmsRequest;
import com.project.remotemotormonitoring.api.dto.SmsResponse;
import com.project.remotemotormonitoring.service.Util.MobileNumberFormatter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SMSServiceImpl implements SMSService {
    private final RestTemplate restTemplate;
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;
    @Value("${twilio.account.authToken}")
    private String AUTH_TOKEN;
    @Value("${twilio.account.number}")
    private String SMS_SENDER_NUMBER;
    @Value("${twilio.account.test}")
    private String RECEIVER_NUMBER;
    @Value("${clickatel.http.api.key}")
    private String HTTP_API_KEY;
    @Value("${clickatel.http.send.url}")
    private String SINGLE_SMS_URL;

    @Override
    public void sendSms(String destination, String text) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        //String phoneNumber = MobileNumberFormatter.formatNumberToInternational(destination);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(RECEIVER_NUMBER),
                        new com.twilio.type.PhoneNumber(SMS_SENDER_NUMBER),
                        text)
                .create();
        log.info("twilio sms response: {}", message);

    }

    @Override
    public void sendSmsWithClickatell(SmsRequest smsRequest) {

        ResponseEntity<SmsResponse> responseEntity = restTemplate.getForEntity(
                SINGLE_SMS_URL,
                SmsResponse.class,
                HTTP_API_KEY,
                MobileNumberFormatter.formatNumberToInternational(smsRequest.getTo()),
                smsRequest.getContent());
        log.info("sms response: {}", responseEntity.getBody());
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("response..{}", responseEntity.getBody());

        }
    }
}

