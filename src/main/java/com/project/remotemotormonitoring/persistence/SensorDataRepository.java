package com.project.remotemotormonitoring.persistence;

import com.project.remotemotormonitoring.domain.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData ,Long> {
    @Query("SELECT sd FROM SensorData sd " +
            "WHERE sd.timeStamp = (SELECT MAX(sd2.timeStamp) FROM SensorData sd2 WHERE sd2.motor = sd.motor)")
    List<SensorData> findLatestSensorDataForEachMotor();
}
