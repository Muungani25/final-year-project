package com.project.remotemotormonitoring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Thresholds {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String current;
    private String temperature;
    private String vibrations;
    @OneToOne
    private Motor motor;
}
