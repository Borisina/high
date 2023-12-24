package com.kolya.high.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kolya.high.model.*;
import com.kolya.high.repo.UserRepository;
import com.kolya.high.repo.DoctorRepository;
import com.kolya.high.repo.PatientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private MockMvc mockMvc;
    private User user1;
    private User user2;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        user1 = new User();
        user1.setUsername("Doctor");
        user1.setRoles(Collections.singleton(Role.valueOf("DOCTOR")));

        doctor = new Doctor();
        doctor.setName("Doctor");
        doctor.setSpecialization("Specialization");
        doctor.setId(1L);
        doctor.setUser(user1);

        user2 = new User();
        user2.setUsername("Bob");
        user2.setRoles(Collections.singleton(Role.valueOf("PATIENT")));

        patient = new Patient();
        patient.setName("Bob");
        patient.setId(1L);
        patient.setUser(user2);


    }

    @Test
    public void testRegisterDoctor() throws Exception {
        when(userRepository.existsByUsername(any(String.class))).thenReturn(false);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user1);
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("test");
        signupRequest.setPassword("pass");
        signupRequest.setName("doctor");
        signupRequest.setSpecialization("specialization");

        mockMvc.perform(post("/auth/signup/doctor")
                        .content(convertToJson(signupRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterPatient() throws Exception {
        when(userRepository.existsByUsername(any(String.class))).thenReturn(false);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user2);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("test");
        signupRequest.setPassword("pass");
        signupRequest.setName("patient");

        mockMvc.perform(post("/auth/signup/patient")
                        .content(convertToJson(signupRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String convertToJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

