package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.AgeCategoryDto;
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

    @GetMapping("/athlete/{athleteId}/season/{seasonId}")
    public ResponseEntity<AgeCategoryDto> getAgeCategoryByAthleteIdAndSeasonId(@PathVariable Integer athleteId, @PathVariable Integer seasonId) {
        var ageCategory = service.getAgeCategoryByAthleteIdAndSeasonId(athleteId, seasonId);
        return new ResponseEntity<>(ageCategory, HttpStatus.OK);
    }

}
