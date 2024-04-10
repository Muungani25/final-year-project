package com.project.remotemotormonitoring.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Audit {
   private LocalTime timeCreated;
   private LocalDate dateCreated;
}
