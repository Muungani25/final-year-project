package com.project.remotemotormonitoring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThresholdRequest {
    private String current;
    private String temperature;
    private String vibrations;
    private String motorName;
}
