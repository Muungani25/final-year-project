package com.project.remotemotormonitoring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Motor motor;
    private String status;
    private String upTime;
    @Column
    private String temperatureValue;
    @Column
    private String currentValue;
    @Column
    private String vibrationValue;
    @Column
    private LocalTime timeStamp;
    @Column
    private LocalDate date;
}
