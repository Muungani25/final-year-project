package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.MotorDto;
import com.project.remotemotormonitoring.api.dto.ThresholdRequest;
import com.project.remotemotormonitoring.domain.Motor;
import com.project.remotemotormonitoring.domain.Thresholds;
import com.project.remotemotormonitoring.persistence.MotorRepository;
import com.project.remotemotormonitoring.persistence.ThresholdsRepository;
import com.project.remotemotormonitoring.service.Util.Image;
import com.project.remotemotormonitoring.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService{
    private final MotorRepository motorRepository;
    private final ThresholdsRepository thresholdsRepository;
    @Override
    public ResponseEntity<String> addMotor(MotorDto motorDto) {
        if(motorRepository.findByMotorName(motorDto.getMotorName()).isEmpty()){

            motorRepository.save(Motor.builder()
                    .motorName(motorDto.getMotorName())
                    .image("")
                    .build());
            return ResponseEntity.ok("motor added successfully");
        }
else {
    return ResponseEntity.ok("motor already exists");
        }
    }

    @Override
    public List<Motor> getAllMotors() {
        return motorRepository.findAll();
    }

    @Override
    public byte[] getImage() throws IOException {

        return Image.readImage("C:\\Users\\Administrator\\Pictures\\project\\pump1.png");
    }

    @Override
    public ResponseEntity<String> setThresholdValues(ThresholdRequest request) {
        if(thresholdsRepository.findAll().isEmpty()){
            thresholdsRepository.save(Thresholds.builder()
                            .vibrations(request.getVibrations()+" G")
                            .current(request.getCurrent()+" A")
                            .temperature(request.getTemperature()+" °C")
                    .build());
            return ResponseEntity.ok("Threshold values set successfully");
        }else {
            return ResponseEntity.ok("Threshold values already set please update");
        }

    }

    @Override
    public List<Thresholds> getSetThresholdValues() {
        if(thresholdsRepository.findAll().isEmpty()) throw new ResourceNotFoundException("no threshold values set");
        return thresholdsRepository.findAll();
    }

    @Override
    public ResponseEntity<String> updateThresholdValues(ThresholdRequest request) {
        var current= thresholdsRepository.findById(0L);

        return null;
    }
}
