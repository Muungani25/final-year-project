package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.MotorDto;
import com.project.remotemotormonitoring.api.dto.Response;
import com.project.remotemotormonitoring.api.dto.ThresholdRequest;
import com.project.remotemotormonitoring.domain.Motor;
import com.project.remotemotormonitoring.domain.Thresholds;
import com.project.remotemotormonitoring.persistence.MotorRepository;
import com.project.remotemotormonitoring.persistence.ThresholdsRepository;
import com.project.remotemotormonitoring.service.Util.Image;
import com.project.remotemotormonitoring.service.exceptions.ResourceAlreadyExists;
import com.project.remotemotormonitoring.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {
    private final MotorRepository motorRepository;
    private final ThresholdsRepository thresholdsRepository;

    @Override
    public ResponseEntity<String> addMotor(MotorDto motorDto) {
        if (motorRepository.findByMotorName(motorDto.getMotorName()).isEmpty()) {

            motorRepository.save(Motor.builder()
                    .motorName(motorDto.getMotorName())

                    .build());
            return ResponseEntity.ok("motor added successfully");
        } else {
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
    public ResponseEntity<Response> setThresholdValues(ThresholdRequest request) {

        var threshold= thresholdsRepository.findThresholdsByMotor_MotorName(request.getMotorName());
        var motor= motorRepository.findByMotorName(request.getMotorName())
                .orElseThrow(()-> new ResourceNotFoundException("motor does not exist"));


        if (threshold.isEmpty()) {

            thresholdsRepository.save(Thresholds.builder()
                    .vibrations(request.getVibrations() )
                    .current(request.getCurrent() )
                    .temperature(request.getTemperature() )
                            .motor(motor)
                    .build());
            return ResponseEntity.ok(Response.builder()
                            .message("Threshold values set successfully")
                    .build());
        } else {
            ;throw new ResourceAlreadyExists("Threshold values already set please update");
        }

    }

    @Override
    public List<Thresholds> getSetThresholdValues() {
        if (thresholdsRepository.findAll().isEmpty()) throw new ResourceNotFoundException("no threshold values set");
        return thresholdsRepository.findAll();
    }

    @Override
    public ResponseEntity<Response> updateThresholdValues(ThresholdRequest request) {
        var currentThreshold = thresholdsRepository.findThresholdsByMotor_MotorName(request.getMotorName())
                .orElseThrow(()-> new ResourceNotFoundException("motor does not exist"));

        currentThreshold.setCurrent(request.getCurrent());
            currentThreshold.setTemperature(request.getTemperature());
            currentThreshold.setVibrations(request.getVibrations());

            thresholdsRepository.save(currentThreshold);

            return ResponseEntity.ok(Response.builder()
                    .message("Threshold values updated successfully")
                    .build());
        }



    @Override
    public ResponseEntity<Response> deleteThreshold() {
        thresholdsRepository.deleteAll();
        return ResponseEntity.ok(Response.builder()
                .message("Threshold values deleted")
                .build());
    }

    private Thresholds getFirstThreshold() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Thresholds> allThresholds = thresholdsRepository.findAll(sort);

        if (!allThresholds.isEmpty()) {

            return allThresholds.get(0);
        } else {
            throw new ResourceNotFoundException("no threshold values found");
        }
    }
}