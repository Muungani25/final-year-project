package com.project.remotemotormonitoring.persistence;

import com.project.remotemotormonitoring.domain.Thresholds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThresholdsRepository extends JpaRepository<Thresholds,Long> {


}
