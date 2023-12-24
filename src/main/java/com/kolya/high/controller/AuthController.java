package com.kolya.high.controller;

import com.kolya.high.model.*;
import com.kolya.high.repo.DoctorRepository;
import com.kolya.high.repo.PatientRepository;
import com.kolya.high.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Locale;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup/{role}")
    public ResponseEntity<?> registerDoctor(@PathVariable String role, @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        role = role.toUpperCase();
        user.setRoles(Collections.singleton(Role.valueOf(role)));

        userRepository.save(user);
        String response=null;
        if (role.equals("DOCTOR")){
            Doctor doctor = new Doctor();
            doctor.setName(signupRequest.getName());
            doctor.setSpecialization(signupRequest.getSpecialization());
            doctor.setUser(user);
            doctorRepository.save(doctor);
            response = "Doctor registered successfully!";
        }else if(role.equals("PATIENT")){
            Patient patient = new Patient();
            patient.setName(signupRequest.getName());
            patient.setUser(user);
            patientRepository.save(patient);
            response = "Patient registered successfully!";
        }

        return ResponseEntity.ok(response);
    }
}
