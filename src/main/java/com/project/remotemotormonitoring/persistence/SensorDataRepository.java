package com.project.remotemotormonitoring.persistence;

import com.project.remotemotormonitoring.domain.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData ,Long> {
    @Query("SELECT sd FROM SensorData sd " +
            "WHERE sd.id IN " +
            "(SELECT MAX(sd2.id) FROM SensorData sd2 GROUP BY sd2.motor)")
    List<SensorData> findLatestSensorDataForEachMotor();


}
