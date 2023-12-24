package com.kolya.high.service;

import java.util.List;

import com.kolya.high.model.Prescription;
import com.kolya.high.repo.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public void savePrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }


    public List<Prescription> findByPatientId(Long id) {
        return prescriptionRepository.findByPatientId(id);
    }

    public List<Prescription> findByDoctorId(Long id) {
        return prescriptionRepository.findByDoctorId(id);
    }

    public List<Prescription> findByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        return prescriptionRepository.findByDoctorIdAndPatientId(doctorId,patientId);
    }
}
