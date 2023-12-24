package com.kolya.high.controller;

import com.kolya.high.repo.HealthcareFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthcareFacilityController {

    @Autowired
    private HealthcareFacilityRepository healthcareFacilityRepository;

    @GetMapping("/facilities")
    public String getFacilities(Model model) {
        model.addAttribute("facilities", healthcareFacilityRepository.findAll());
        return "facilities";
    }
}
