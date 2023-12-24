package com.kolya.high.controller;

import com.kolya.high.model.Patient;
import com.kolya.high.service.PatientService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        patientService.updatePatient(id, patientDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
}
