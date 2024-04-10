package com.project.remotemotormonitoring.persistence;

import com.project.remotemotormonitoring.domain.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MotorRepository extends JpaRepository<Motor,Long> {
    Optional<Motor> findByMotorName(String name);

//    @Query("SELECT m FROM Motor m LEFT JOIN FETCH m.sensorData s WHERE s.timeStamp = (SELECT MAX(s2.timeStamp) FROM SensorData s2 WHERE s2.motor = m)")
//    List<Motor> findAllWithLatestSensorData();
}

