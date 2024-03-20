package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.service.AthleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    private final AthleteService service;

    public AthleteController(AthleteService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Athlete> addAthlete(@RequestBody AthleteDto athleteDto) {
        var savedAthlete = service.createAthlete(athleteDto);
        return new ResponseEntity<>(savedAthlete, HttpStatus.CREATED);
    }

}
