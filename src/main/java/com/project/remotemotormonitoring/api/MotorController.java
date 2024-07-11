package com.project.remotemotormonitoring.api;

import com.project.remotemotormonitoring.api.dto.MotorDto;
import com.project.remotemotormonitoring.api.dto.SensorDataDto;
import com.project.remotemotormonitoring.domain.Motor;
import com.project.remotemotormonitoring.domain.SensorData;
import com.project.remotemotormonitoring.service.MotorService;
import com.project.remotemotormonitoring.service.SensorDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/motor")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "authorization")
public class MotorController {
    private final MotorService motorService;
    private final SensorDataService sensorDataService;

    @PostMapping("/add")
    @Operation(summary = "add new motor")
    public ResponseEntity<?> addMotor(@RequestBody MotorDto name) {
        return motorService.addMotor(name);
    }
    @Operation(summary = "add data from sensors")
    @PostMapping("/add_sensor_data")
    public ResponseEntity<?> addSensorData(@RequestBody SensorDataDto sensorData) {
        log.info("receiving data.....{}",sensorData);
        return sensorDataService.addSensorData(sensorData);
    }

    @Operation(summary = "get list of all motors")
    @GetMapping("/all-motors")
    public List<Motor> getAllMotors() {
        return motorService.getAllMotors();
    }
    @Operation(summary = "get latest sensor data for each motor")
    @GetMapping("/latest_sensor_data")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sensorDataService.getLatestSensorData())
                ;
    }
    @Operation(summary = "get motor image")
    @GetMapping("/image")
    public ResponseEntity<?> getMotorImage() throws IOException {
        var image=motorService.getImage();
        return ResponseEntity.status(200)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


}
