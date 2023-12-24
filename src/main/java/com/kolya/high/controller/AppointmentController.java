package com.kolya.high.controller;

import com.kolya.high.model.Appointment;
import com.kolya.high.model.Doctor;
import com.kolya.high.model.Patient;
import com.kolya.high.service.AppointmentService;
import com.kolya.high.service.DoctorService;
import com.kolya.high.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/doctor-{doctorId}/patient-{patientId}")
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment, @PathVariable Long patientId, @PathVariable Long doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        appointment.setDoctor(doctor);
        Patient patient = patientService.findById(patientId);
        appointment.setPatient(patient);
        String error = appointmentService.createAppointment(appointment);
        if (error!=null){
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Appointment created.", HttpStatus.CREATED);
    }

    @GetMapping("/doctor/{id}")
    public List<Appointment> getAllAppointmentsByDoctor(@PathVariable Long id) {
        return appointmentService.findByDoctorId(id);
    }

    @GetMapping("/patient/{id}")
    public List<Appointment> getAllAppointmentsByPatient(@PathVariable Long id) {
        return appointmentService.findByPatientId(id);
    }
}
