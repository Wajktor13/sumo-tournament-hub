package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.dto.ClubDto;
import com.sumotournamenthub.backend.service.AthleteService;
import com.sumotournamenthub.backend.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDto> getAll() {
        return clubService.getAllClubs().stream().map(this::convertToDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDto> getById(@PathVariable Integer id) {
        ClubDto clubdto = convertToDto(clubService.getClubById(id));
        return new ResponseEntity<>(clubdto, HttpStatus.OK);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<Set<AthleteDto>> getAllAthletesByClubId(@PathVariable int clubId) {
        Set<Athlete> athletes = clubService.getAllByClubId(clubId);
        Set<AthleteDto> athleteDto = athletes.stream().map(AthleteService::convertToDto).collect(Collectors.toSet());
        return athleteDto.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(athleteDto);
    }

    private ClubDto convertToDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .country(club.getCountry())
                .name(club.getName())
                .build();
    }
}
