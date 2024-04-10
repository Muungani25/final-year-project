package com.project.remotemotormonitoring.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSServiceImpl implements SMSService {
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;
    @Value("${twilio.account.authToken}")
    private String AUTH_TOKEN;
    @Value("${twilio.account.number}")
    private String SMS_SENDER_NUMBER;
    @Value("${twilio.account.test}")
    private String RECEIVER_NUMBER;

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
}
