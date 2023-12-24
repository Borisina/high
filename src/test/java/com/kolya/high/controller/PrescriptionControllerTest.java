package com.kolya.high.controller;

import com.kolya.high.model.Doctor;
import com.kolya.high.model.Patient;
import com.kolya.high.model.Prescription;
import com.kolya.high.service.DoctorService;
import com.kolya.high.service.PatientService;
import com.kolya.high.service.PrescriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PrescriptionControllerTest {

    @Mock
    private PrescriptionService prescriptionService;

    @Mock
    private PatientService patientService;

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private PrescriptionController prescriptionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(prescriptionController).build();
    }

    @Test
    public void testCreatePrescription() throws Exception {
        // Arrange
        Prescription prescription = new Prescription();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        when(doctorService.findDoctorByUserId(any())).thenReturn(doctor);
        when(patientService.findById(any(Long.class))).thenReturn(patient);

        // Act & Assert
        // Replace "prescriptionJson" with JSON string represents your Prescription object
        mockMvc.perform(post("/prescriptions/doctor/create/1")
                        .content("{\"medicine\":\"Sleep!\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Continue with your other test methods (getPrescriptionsByDoctor and getPrescriptions)

    @Test
    public void testGetPrescriptionsByDoctor() throws Exception {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor);
        //Set the rest of the properties as needed

        List<Prescription> prescriptions = Arrays.asList(prescription);
        when(doctorService.findDoctorByUserId(any())).thenReturn(doctor);
        when(prescriptionService.findByDoctorId(any(Long.class))).thenReturn(prescriptions);

        // Act & Assert
        mockMvc.perform(get("/prescriptions/doctor"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPrescriptions() throws Exception {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L);
        Prescription prescription = new Prescription();
        prescription.setPatient(patient);
        //Set the rest of the properties as needed

        List<Prescription> prescriptions = Arrays.asList(prescription);
        when(patientService.findPatientByUserId(any())).thenReturn(patient);
        when(prescriptionService.findByPatientId(any(Long.class))).thenReturn(prescriptions);

        // Act & Assert
        mockMvc.perform(get("/prescriptions/patient"))
                .andExpect(status().isOk());
    }
}