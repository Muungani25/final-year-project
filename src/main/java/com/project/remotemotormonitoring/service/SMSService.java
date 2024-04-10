package com.project.remotemotormonitoring.service;
@FunctionalInterface
public interface SMSService {
    void sendSms(String destination, String message);
}
