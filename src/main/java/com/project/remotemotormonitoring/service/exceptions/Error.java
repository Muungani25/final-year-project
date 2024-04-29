package com.project.remotemotormonitoring.service.exceptions;

public class Error {
    private final int code;
    private final String message;

    private Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error of(int code, String message) {
        return new Error(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
