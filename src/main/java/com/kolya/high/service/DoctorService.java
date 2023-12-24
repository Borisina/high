package com.kolya.high.service;

import com.kolya.high.model.Doctor;
import com.kolya.high.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findDoctorByUserId(Long id) {
        return doctorRepository.findByUserId(id);
    }

    public Doctor findById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }
}