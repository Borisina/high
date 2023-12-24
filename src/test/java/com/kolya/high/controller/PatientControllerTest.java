package com.kolya.high.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kolya.high.model.Patient;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    public void getAllPatients() throws Exception {
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(new Patient(), new Patient()));
        mockMvc.perform(get("/patients/"))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePatient() throws Exception {
        mockMvc.perform(put("/patients/1")
                        .content(convertToJson(new Patient()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePatient() throws Exception {
        doNothing().when(patientService).deletePatient(anyLong());
        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientById() throws Exception {
        when(patientService.getPatientById(anyLong())).thenReturn(new Patient());
        mockMvc.perform(get("/patients/1"))
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