package com.kolya.high.repo;

import com.kolya.high.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUserId(Long id);
}
