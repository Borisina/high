package com.kolya.high.config;

import com.kolya.high.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/signup/**","/facilities").permitAll()
                        .requestMatchers("/patients/**").hasAnyAuthority("PATIENT","DOCTOR")
                        .requestMatchers("/.+/doctor/**").hasAuthority("DOCTOR")
                        .requestMatchers("/.+/patient/**").hasAuthority("PATIENT")
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic();
        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}

