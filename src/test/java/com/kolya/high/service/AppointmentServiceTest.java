package com.kolya.high.service;

import com.kolya.high.model.Appointment;
import com.kolya.high.model.Doctor;
import com.kolya.high.model.Patient;
import com.kolya.high.repo.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Tell JUnit 5 to use Mockito
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    Appointment appointment;

    @BeforeEach
    public void setUp() {
        appointment = new Appointment();
        appointment.setId(1L); // or any ID
        Doctor doctor = new Doctor();
        doctor.setId(1L); // or any ID
        appointment.setDoctor(doctor);
        Patient patient = new Patient();
        patient.setId(2L); // or any ID
        appointment.setPatient(patient);
        appointment.setAppointmentTime(LocalDateTime.now().plusHours(2));
    }

    @Test
    void whenSaveAppointment_givenDoctorIsFree_thenSuccess() {
        when(appointmentRepository.findByDoctorIdAndAppointmentTime(any(), any()))
                .thenReturn(null);

        when(appointmentRepository.findByPatientIdAndAppointmentTime(any(), any()))
                .thenReturn(null);

        when(appointmentRepository.save(any()))
                .thenReturn(appointment);

        String response = appointmentService.createAppointment(appointment);

        assertEquals(null, response);
    }

    @Test
    void whenSaveAppointment_givenDoctorHasAppointment_thenError() {
        when(appointmentRepository.findByDoctorIdAndAppointmentTime(any(), any()))
                .thenReturn(new Appointment());

        String response = appointmentService.createAppointment(appointment);

        assertEquals("Doctor has appointment at this time.", response);
    }

    @Test
    void whenSaveAppointment_givenPatientHasAppointment_thenError() {
        when(appointmentRepository.findByDoctorIdAndAppointmentTime(any(), any()))
                .thenReturn(null);

        when(appointmentRepository.findByPatientIdAndAppointmentTime(any(), any()))
                .thenReturn(new Appointment());

        String response = appointmentService.createAppointment(appointment);

        assertEquals("Patient has appointment at this time.", response);
    }

    @Test
    void testFindByDoctorId() {
        when(appointmentRepository.findByDoctorId(anyLong()))
                .thenReturn(new ArrayList<>());

        appointmentService.findByDoctorId(1L);

        verify(appointmentRepository, times(1)).findByDoctorId(1L);
    }

    @Test
    void testFindByPatientId() {
        when(appointmentRepository.findByPatientId(anyLong()))
                .thenReturn(new ArrayList<>());

        appointmentService.findByPatientId(1L);

        verify(appointmentRepository, times(1)).findByPatientId(1L);
    }
}