package com.project.remotemotormonitoring.api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data

@Builder
public class SmsRequest {
    private String to;
    private String content;
}
