package com.project.remotemotormonitoring.service.exceptions;

public class FailedToSendEmailException extends RuntimeException{
    public FailedToSendEmailException(String message) {
        super(message);
    }
}
