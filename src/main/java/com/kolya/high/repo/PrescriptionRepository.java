package com.kolya.high.repo;

import com.kolya.high.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatientId(Long id);

    List<Prescription> findByDoctorId(Long id);

    List<Prescription> findByDoctorIdAndPatientId(Long doctorId, Long patientId);
}
