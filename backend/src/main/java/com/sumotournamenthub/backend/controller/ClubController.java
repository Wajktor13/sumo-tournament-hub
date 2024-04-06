package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.dto.ClubDto;
import com.sumotournamenthub.backend.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDto> getAll() {
        return clubService.getAllClubs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDto> getClub(@PathVariable Integer id) {
        var clubdto = clubService.getClub(id);
        return new ResponseEntity<>(clubdto, HttpStatus.OK);
    }

    @GetMapping("/{id}/athletes")
    public ResponseEntity<List<AthleteDto>> getAllAthletes(@PathVariable int id) {
        var athletesByClubId = clubService.getAllAthletes(id);
        return new ResponseEntity<>(athletesByClubId, HttpStatus.OK);
    }

}