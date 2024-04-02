package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
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

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody Competition competition) {
        Competition createdCompetition = competitionService.addCompetition(competition);
        CompetitionDto competitionDto = CompetitionDto.from(createdCompetition);
        return new ResponseEntity<>(competitionDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/ageCategories")
    public ResponseEntity<List<AgeCategoryDto>> getAllAgeCategoriesByCompetitionId(@PathVariable int competitionId) {
        List<AgeCategoryDto> ageCategories = competitionService.getAllAgeCategoriesByCompetitionId(competitionId);
        return ResponseEntity.ok(ageCategories);
    }
}