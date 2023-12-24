package com.kolya.high.service;

import com.kolya.high.exception.ResourceNotFoundException;
import com.kolya.high.model.Patient;
import com.kolya.high.repo.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setName("Bob");
        patient.setId(1L);
    }

    @Test
    public void testGetAllPatients() {
        when(patientRepository.findAll()).thenReturn(Collections.singletonList(patient));

        List<Patient> patients = patientService.getAllPatients();

        assertEquals(1, patients.size());
        assertEquals(patient, patients.get(0));
    }

    @Test
    public void testGetPatientById() {
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        Patient foundPatient = patientService.getPatientById(1L);

        assertEquals(patient, foundPatient);
    }

    @Test
    public void testDeletePatient() {
        doNothing().when(patientRepository).deleteById(any(Long.class));

        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdatePatient() {
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient updatedPatient = patientService.updatePatient(1L, patient);

        assertEquals(patient, updatedPatient);
    }

    @Test
    public void testFindPatientByUserId() {
        when(patientRepository.findByUserId(any(Long.class))).thenReturn(patient);

        Patient foundPatient = patientService.findPatientByUserId(1L);

        assertEquals(patient, foundPatient);
    }

    @Test
    public void testFindById() {
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        Patient foundPatient = patientService.findById(1L);

        assertEquals(patient, foundPatient);
    }
}