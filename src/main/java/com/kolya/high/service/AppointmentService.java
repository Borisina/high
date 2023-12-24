package com.kolya.high.service;

import java.util.List;

import com.kolya.high.model.Appointment;
import com.kolya.high.repo.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public String createAppointment(Appointment appointment) {
        Appointment checkTimeForDoctor = appointmentRepository.findByDoctorIdAndAppointmentTime(appointment.getDoctor().getId(),appointment.getAppointmentTime());
        if (checkTimeForDoctor!=null){
            return "Doctor has appointment at this time.";
        }
        Appointment checkTimeForPatient= appointmentRepository.findByPatientIdAndAppointmentTime(appointment.getPatient().getId(),appointment.getAppointmentTime());
        if (checkTimeForPatient!=null){
            return "Patient has appointment at this time.";
        }
        appointmentRepository.save(appointment);
        return null;
    }

    public List<Appointment> findByDoctorId(Long id) {
        return appointmentRepository.findByDoctorId(id);
    }

    public List<Appointment> findByPatientId(Long id) {
        return appointmentRepository.findByPatientId(id);
    }
}
