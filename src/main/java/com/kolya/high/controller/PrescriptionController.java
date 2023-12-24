package com.kolya.high.controller;

import com.kolya.high.model.Doctor;
import com.kolya.high.model.Patient;
import com.kolya.high.model.Prescription;
import com.kolya.high.model.User;
import com.kolya.high.service.DoctorService;
import com.kolya.high.service.PatientService;
import com.kolya.high.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor/create/{patientId}")
    public void createPrescription(@AuthenticationPrincipal User user,@PathVariable("patientId") Long patientId, @RequestBody Prescription prescription) {
        Doctor doctor = doctorService.findDoctorByUserId(user.getId());
        prescription.setDoctor(doctor);
        Patient patient = patientService.findById(patientId);
        prescription.setPatient(patient);
        prescriptionService.savePrescription(prescription);
    }

    @GetMapping("/doctor")
    public List<Prescription> getPrescriptionsByDoctor(@AuthenticationPrincipal User user, @RequestParam(name="patient",required = false) Long patientId ) {
        Doctor doctor = doctorService.findDoctorByUserId(user.getId());
        if (patientId==null){
            return prescriptionService.findByDoctorId(doctor.getId());
        }else{
            return prescriptionService.findByDoctorIdAndPatientId(doctor.getId(),patientId);
        }
    }

    @GetMapping("/patient")
    public List<Prescription> getPrescriptions(@AuthenticationPrincipal User user) {
        Patient patient = patientService.findPatientByUserId(user.getId());
        return prescriptionService.findByPatientId(patient.getId());
    }
}