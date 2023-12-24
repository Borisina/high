package com.kolya.high.controller;

import com.kolya.high.model.HealthcareFacility;
import com.kolya.high.repo.HealthcareFacilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class HealthcareFacilityControllerTest {

    @Mock
    private HealthcareFacilityRepository healthcareFacilityRepository;

    @InjectMocks
    private HealthcareFacilityController healthcareFacilityController;

    // MockMvc for testing web layers
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(healthcareFacilityController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testGetFacilities() throws Exception {
        HealthcareFacility facility1 = new HealthcareFacility("F1","1",0.00, 1.00);
        HealthcareFacility facility2 = new HealthcareFacility("F2","2",0.00, 2.00);

        List<HealthcareFacility> facilities = Arrays.asList(facility1, facility2);

        when(healthcareFacilityRepository.findAll()).thenReturn(facilities);

        mockMvc.perform(get("/facilities"))
                .andExpect(status().isOk())
                .andExpect(view().name("facilities"))
                .andExpect(model().attributeExists("facilities"))
                .andExpect(model().attribute("facilities", facilities));
    }
}