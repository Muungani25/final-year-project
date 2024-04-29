package com.project.remotemotormonitoring.service;


import com.project.remotemotormonitoring.api.dto.SensorDataDto;
import com.project.remotemotormonitoring.api.dto.SensorDataResponse;
import com.project.remotemotormonitoring.domain.SensorData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SensorDataService {
    ResponseEntity<String> addSensorData(SensorDataDto sensorData) ;
    List<SensorDataResponse> getLatestSensorData();


}
