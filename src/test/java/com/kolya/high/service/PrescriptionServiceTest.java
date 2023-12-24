package com.kolya.high.service;

import com.kolya.high.exception.ResourceNotFoundException;
import com.kolya.high.model.Doctor;
import com.kolya.high.model.Patient;
import com.kolya.high.model.Prescription;
import com.kolya.high.repo.PrescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrescriptionServiceTest {

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionService prescriptionService;

    private Prescription prescription;

    @BeforeEach
    public void setUp() {
        prescription = new Prescription();
        Doctor doctor = new Doctor();
        doctor.setName("Doctor");
        doctor.setSpecialization("Specialization");
        doctor.setId(1L);

        Patient patient = new Patient();
        patient = new Patient();
        patient.setName("Bob");
        patient.setId(1L);

        prescription = new Prescription();
        prescription.setId(1L); //let's assume this ID
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setMedicine("Paracetamol"); //let's assume this medicine
    }

    @Test
    public void testSavePrescription() {
        when(prescriptionRepository.save(any(Prescription.class))).thenReturn(prescription);

        prescriptionService.savePrescription(prescription);

        verify(prescriptionRepository, times(1)).save(prescription);
    }

    @Test
    public void testFindByPatientId() {
        when(prescriptionRepository.findByPatientId(any(Long.class))).thenReturn(Collections.singletonList(prescription));

        List<Prescription> prescriptions = prescriptionService.findByPatientId(1L);

        assertEquals(1, prescriptions.size());
        assertEquals(prescription, prescriptions.get(0));
    }

    @Test
    public void testFindByDoctorId() {
        when(prescriptionRepository.findByDoctorId(any(Long.class))).thenReturn(Collections.singletonList(prescription));

        List<Prescription> prescriptions = prescriptionService.findByDoctorId(1L);

        assertEquals(1, prescriptions.size());
        assertEquals(prescription, prescriptions.get(0));
    }

    @Test
    public void testFindByDoctorIdAndPatientId() {
        when(prescriptionRepository.findByDoctorIdAndPatientId(any(Long.class), any(Long.class))).thenReturn(Collections.singletonList(prescription));

        List<Prescription> prescriptions = prescriptionService.findByDoctorIdAndPatientId(1L, 1L);

        assertEquals(1, prescriptions.size());
        assertEquals(prescription, prescriptions.get(0));
    }
}