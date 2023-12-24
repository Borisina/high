package com.kolya.high.repo;

import com.kolya.high.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByUserId(Long id);
}
