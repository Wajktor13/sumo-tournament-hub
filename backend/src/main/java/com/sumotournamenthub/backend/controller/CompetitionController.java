package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.service.CompetitionService;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public List<CompetitionDto> getAllCompetitions() {
        return competitionService.getAllCompetitions().stream().map(CompetitionDto::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> getCompetitionById(@PathVariable Integer id) {
        return ResponseEntity.ok(CompetitionDto.from(competitionService.getCompetitionEntity(id)));
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody Competition competition) {
        Competition createdCompetition = competitionService.addCompetition(competition);
        CompetitionDto competitionDto = CompetitionDto.from(createdCompetition);
        return new ResponseEntity<>(competitionDto, HttpStatus.CREATED);
    }
}