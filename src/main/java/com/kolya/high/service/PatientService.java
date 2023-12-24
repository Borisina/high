package com.kolya.high.service;

import java.util.List;

import com.kolya.high.exception.ResourceNotFoundException;
import com.kolya.high.model.Patient;
import com.kolya.high.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        patient.setName(patientDetails.getName());
        Patient updatedPatient = patientRepository.save(patient);
        return updatedPatient;
    }

    public Patient findPatientByUserId(Long id) {
        return patientRepository.findByUserId(id);
    }

    public Patient findById(Long patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }
}
