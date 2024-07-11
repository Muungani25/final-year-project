package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.SmsRequest;
import com.project.remotemotormonitoring.api.dto.SmsResponse;

public interface SMSService {
    void sendSms(String destination, String message);

    void sendSmsWithClickatell(SmsRequest smsRequest);
}
