package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.MotorDto;
import com.project.remotemotormonitoring.api.dto.Response;
import com.project.remotemotormonitoring.api.dto.ThresholdRequest;
import com.project.remotemotormonitoring.domain.Motor;
import com.project.remotemotormonitoring.domain.Thresholds;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface MotorService {
    ResponseEntity<String> addMotor(MotorDto motorDto);
    List<Motor> getAllMotors();
    byte[] getImage() throws IOException;

    ResponseEntity<Response> setThresholdValues(ThresholdRequest request);
    List<Thresholds> getSetThresholdValues();

    ResponseEntity<Response> updateThresholdValues(ThresholdRequest request);

    ResponseEntity<Response> deleteThreshold();
}
