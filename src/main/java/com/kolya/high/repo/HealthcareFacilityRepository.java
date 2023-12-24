package com.kolya.high.repo;

import com.kolya.high.model.HealthcareFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthcareFacilityRepository extends JpaRepository<HealthcareFacility, Long> {
}
