package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.service.AthleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    private final AthleteService service;

    public AthleteController(AthleteService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> createAthlete(@RequestBody AthleteDto athleteDto) {
        try {
            service.validate(athleteDto);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        var savedAthlete = service.createAthlete(athleteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAthlete);
    }
}