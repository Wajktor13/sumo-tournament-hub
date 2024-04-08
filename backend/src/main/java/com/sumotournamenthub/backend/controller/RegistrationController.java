package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.RegistrationDto;
import com.sumotournamenthub.backend.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService registrationService) {
        this.service = registrationService;
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> registerAthlete(@RequestBody RegistrationDto dto) {
        var registration = service.registerAthleteForWeightCategory(dto);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

}
