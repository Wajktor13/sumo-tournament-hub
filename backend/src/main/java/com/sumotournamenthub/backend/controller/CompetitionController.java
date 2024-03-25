package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.CompetitionDto;
import com.sumotournamenthub.backend.service.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return competitionService.getAllCompetitions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> getCompetitionById(@PathVariable int id)
    {
        Optional<CompetitionDto> competitionDto = competitionService.getCompetitionById(id);

        if (competitionDto.isPresent())
        {
            return ResponseEntity.ok(competitionDto.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody CompetitionDto competitionDto)
    {
        Optional<CompetitionDto> competitionResponse = competitionService.createCompetition(competitionDto);

        if (competitionResponse.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(competitionResponse.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
