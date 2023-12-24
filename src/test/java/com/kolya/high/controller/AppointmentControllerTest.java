package com.kolya.high.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kolya.high.model.Appointment;
import com.kolya.high.model.Doctor;
import com.kolya.high.model.Patient;
import com.kolya.high.service.AppointmentService;
import com.kolya.high.service.DoctorService;
import com.kolya.high.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private DoctorService doctorService;

    @Mock
    private PatientService patientService;

    private MockMvc mockMvc;

    // Assume you have a method to convert object to json string:
    private String convertToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Register the JavaTimeModule to ObjectMapper to support java.time.LocalDateTime serialization
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        //initialize your entities here
    }

    @Test
    public void testCreateAppointment() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(1L); // or any ID
        Doctor doctor = new Doctor();
        doctor.setId(1L); // or any ID
        appointment.setDoctor(doctor);
        Patient patient = new Patient();
        patient.setId(2L); // or any ID
        appointment.setPatient(patient);
        appointment.setAppointmentTime(LocalDateTime.now().plusHours(2));

        when(doctorService.findById(anyLong())).thenReturn(doctor);
        when(patientService.findById(anyLong())).thenReturn(patient);
        when(appointmentService.createAppointment(any())).thenReturn(null);

        mockMvc.perform(post("/appointments/doctor-1/patient-1")
                        .content(convertToJson(appointment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Appointment created."));

        verify(appointmentService, times(1)).createAppointment(any());
    }

    @Test
    public void testFindAllByDoctorId() throws Exception {
        when(appointmentService.findByDoctorId(anyLong())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/appointments/doctor/1"))
                .andExpect(status().isOk());

        verify(appointmentService, times(1)).findByDoctorId(anyLong());
    }

    @Test
    public void testFindAllByPatientId() throws Exception {
        when(appointmentService.findByPatientId(anyLong())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/appointments/patient/1"))
                .andExpect(status().isOk());

        verify(appointmentService, times(1)).findByPatientId(anyLong());
    }
}