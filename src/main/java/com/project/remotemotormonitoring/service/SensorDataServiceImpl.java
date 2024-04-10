package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.SensorDataDto;
import com.project.remotemotormonitoring.api.dto.SensorDataResponse;
import com.project.remotemotormonitoring.domain.SensorData;
import com.project.remotemotormonitoring.domain.Status;
import com.project.remotemotormonitoring.persistence.MotorRepository;
import com.project.remotemotormonitoring.persistence.SensorDataRepository;
import com.project.remotemotormonitoring.service.Util.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorDataServiceImpl implements SensorDataService{
    private final MotorRepository motorRepository;
    private final SensorDataRepository sensorDataRepository;
    @Override
    public ResponseEntity<String> addSensorData(SensorDataDto sensorData) {
        var motor= motorRepository.findByMotorName(sensorData.motorName);
        motor.ifPresent(value -> sensorDataRepository.save(SensorData.builder()
                .currentValue(sensorData.getCurrent()+" A")
                .temperatureValue(sensorData.getTemperature()+" °C")
                .vibrationValue(sensorData.getVibrations()+" G")
                .timeStamp(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
                .date(LocalDate.now())
                .status(Status.RUNNING.toString())
                .motor(value)
                .build()));
        return ResponseEntity.ok("data added");
    }

    @Override
    public List<SensorDataResponse> getLatestSensorData() {
        return  sensorDataRepository.findLatestSensorDataForEachMotor().stream().map(sensorData -> {
            try {
                return buildResponse(sensorData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();

    }

    private SensorDataResponse buildResponse(SensorData sensorData) throws IOException {
        return SensorDataResponse.builder()
              //  .image(Image.readImage("C:\\Users\\USER\\Documents\\project\\images\\pump1.png"))
                .currentValue(sensorData.getCurrentValue())
                .temperatureValue(sensorData.getTemperatureValue())
                .date(sensorData.getDate())
                .vibrationValue(sensorData.getVibrationValue())
                .motorName(sensorData.getMotor().getMotorName())
                .status(sensorData.getStatus())
                .timesStamp(sensorData.getTimeStamp())
                .build();
    }
}
