package com.kolya.high.model;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String name;
    private String specialization;
}