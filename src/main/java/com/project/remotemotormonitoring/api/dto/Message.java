package com.project.remotemotormonitoring.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {
    private String apiMessageId;
    private boolean accepted;
    @JsonProperty("to")
    private String myto;
    private int errorCode;
    private String error;
    private String errorDescription;
}
