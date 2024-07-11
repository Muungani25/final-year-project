package com.project.remotemotormonitoring.service;

import com.project.remotemotormonitoring.api.dto.SensorDataDto;
import com.project.remotemotormonitoring.api.dto.SensorDataResponse;
import com.project.remotemotormonitoring.domain.SensorData;
import com.project.remotemotormonitoring.domain.Status;
import com.project.remotemotormonitoring.persistence.MotorRepository;
import com.project.remotemotormonitoring.persistence.SensorDataRepository;
import com.project.remotemotormonitoring.service.Util.Image;
import com.project.remotemotormonitoring.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SensorDataServiceImpl implements SensorDataService{
    private final MotorRepository motorRepository;
    private final SensorDataRepository sensorDataRepository;
    @Override
    public ResponseEntity<String> addSensorData(SensorDataDto sensorData) {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
       var formattedTime= formatter.format(LocalTime.now());
        var localTime=LocalTime.parse(formattedTime, formatter);

        log.info("time...{}",localTime);
        long milliseconds = Long.parseLong(sensorData.getUpTime());
        double vibValue=Double.parseDouble(sensorData.getVibrations());
        String vibFormatted=String.format("%.3f",vibValue);
        double minutes = milliseconds / 60000.0;
        String formattedMinutes = String.format("%.2f", minutes);
        var motor = motorRepository.findByMotorName(sensorData.getMotorName())
                .orElseThrow(() -> new ResourceNotFoundException("Motor not found"));
        sensorDataRepository.save(SensorData.builder()
                .currentValue(formatValue(sensorData.getCurrent())+" A")
                .temperatureValue(formatValue(sensorData.getTemperature())+" °C")
                .vibrationValue(vibFormatted+" Hz")
                .timeStamp(localTime)
                .date(LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .status(sensorData.getStatus())
                .motor(motor)
                .upTime(formattedMinutes)
                .build());
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
                .currentValue((sensorData.getCurrentValue()))
                .temperatureValue(sensorData.getTemperatureValue())
                .date(sensorData.getDate())
                .vibrationValue(sensorData.getVibrationValue())
                .motorName(sensorData.getMotor().getMotorName())
                .status(sensorData.getStatus())
                .timesStamp(sensorData.getTimeStamp())
                .build();
    }

   private String formatValue(String value){
        double number = Double.parseDouble(value);
       return String.format("%.2f", number);
    }
}
