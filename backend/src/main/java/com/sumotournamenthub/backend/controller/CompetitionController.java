package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.service.CompetitionService;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody Competition competition) {
        Competition createdCompetition = competitionService.addCompetition(competition);
        CompetitionDto competitionDto = CompetitionDto.from(createdCompetition);
        return new ResponseEntity<>(competitionDto, HttpStatus.CREATED);
    }

    @GetMapping("/{competitionId}/ageCategories")
    public ResponseEntity<Set<AgeCategory>> getAllAgeCategoriesByCompetitionId(@PathVariable int competitionId) {
        Set<AgeCategory> ageCategories = competitionService.getAllAgeCategoriesByCompetitionId(competitionId);
        return ResponseEntity.ok(ageCategories);
    }
}