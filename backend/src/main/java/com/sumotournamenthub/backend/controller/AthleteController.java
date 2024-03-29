package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.service.AthleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    private final AthleteService service;

    public AthleteController(AthleteService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<AthleteDto> addAthlete(@RequestBody AthleteDto athleteDto) {
        Athlete savedAthlete = service.createAthlete(athleteDto);
        AthleteDto savedAthleteDto = AthleteDto.convertToDto(savedAthlete);
        return new ResponseEntity<>(savedAthleteDto, HttpStatus.CREATED);
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<Set<AthleteDto>> getAllAthletesByClubId(@PathVariable int clubId) {
        Set<Athlete> athletes = service.getAllByClubId(clubId);
        Set<AthleteDto> athleteDto = athletes.stream().map(AthleteDto::convertToDto).collect(Collectors.toSet());
        return athleteDto.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(athleteDto);
    }

}