package com.project.remotemotormonitoring.persistence;

import com.project.remotemotormonitoring.domain.Thresholds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThresholdsRepository extends JpaRepository<Thresholds,Long> {

    Optional<Thresholds> findThresholdsByMotor_MotorName(String motor_motorName);


}
