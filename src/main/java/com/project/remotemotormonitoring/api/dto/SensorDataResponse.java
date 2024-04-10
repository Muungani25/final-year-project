package com.project.remotemotormonitoring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDataResponse {
  //  private byte[] image;
    private String motorName;
    private String status;
    private String temperatureValue;
    private String currentValue;
    private String vibrationValue;
    private LocalTime timesStamp;
    private LocalDate date;
}
