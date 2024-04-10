package com.project.remotemotormonitoring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDataDto {
    private String current;
    private String temperature;
    private String vibrations;
    public String motorName;


}
