package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import com.sumotournamenthub.backend.service.CompetitionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
public class CompetitionController
{
    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService)
    {
        this.competitionService = competitionService;
    }

    @GetMapping("getAllCompetitions")
    public List<CompetitionDto> getAllCompetitions()
    {
        return competitionService.getAllCompetitions().stream().map(competitionService::convertToDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompetitionById(@PathVariable int id)
    {
        try
        {
            Competition competition = competitionService.getCompetitionById(id);
            return ResponseEntity.ok(competitionService.convertToDto(competition));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody CompetitionDto competitionDto)
    {
        Competition competition = competitionService.createCompetition(competitionService.convertToEntity(competitionDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(competitionService.convertToDto(competition));
    }
}
