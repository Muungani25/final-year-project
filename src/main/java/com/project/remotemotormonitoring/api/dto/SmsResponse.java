package com.project.remotemotormonitoring.api.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class SmsResponse {
    private List<Message> messages;
    private int responseCode;

}
