package com.project.remotemotormonitoring.api;


import com.project.remotemotormonitoring.api.dto.ThresholdRequest;
import com.project.remotemotormonitoring.service.MotorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/threshold")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "authorization")
public class ThresholdController {
private final MotorService motorService;

@PostMapping("/set_values")
    public ResponseEntity<?> setThresholdValues(@RequestBody ThresholdRequest request){
        return motorService.setThresholdValues(request);
    }

    @GetMapping("/get_current_values")
   public List<?> getSetThresholds(){
    return  motorService.getSetThresholdValues();
    }

    @PutMapping("/update_thresholds")
    public ResponseEntity<?> updateThresholdValues(@RequestBody ThresholdRequest request){
    return motorService.updateThresholdValues(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteThreshold(){
    return motorService.deleteThreshold();
    }
}

