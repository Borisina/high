package com.kolya.high.service;

import com.kolya.high.repo.DoctorRepository;
import com.kolya.high.model.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;

    @BeforeEach
    public void setup() {
        doctor = new Doctor();
        doctor.setName("Doctor");
        doctor.setSpecialization("Specialization");
        doctor.setId(1L);
    }

    @Test
    public void testFindDoctorByUserId() {
        when(doctorRepository.findByUserId(any(Long.class))).thenReturn(doctor);

        Doctor foundDoctor = doctorService.findDoctorByUserId(1L);

        assertEquals(doctor, foundDoctor);
    }

    @Test
    public void testFindById() {
        when(doctorRepository.findById(any(Long.class))).thenReturn(Optional.of(doctor));

        Doctor foundDoctor = doctorService.findById(1L);

        assertEquals(doctor, foundDoctor);
    }
}