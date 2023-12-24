package com.kolya.high.repo;

import com.kolya.high.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByDoctorIdAndAppointmentTime(Long id, LocalDateTime appointmentTime);

    Appointment findByPatientIdAndAppointmentTime(Long id, LocalDateTime appointmentTime);

    List<Appointment> findByDoctorId(Long id);

    List<Appointment> findByPatientId(Long id);
}
