package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.service.AgeCategoryService;
import com.sumotournamenthub.backend.service.SeasonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    private final SeasonService seasonService;
    private final AgeCategoryService ageCategoryService;

    public SeasonController(SeasonService seasonService, AgeCategoryService ageCategoryService) {
        this.seasonService = seasonService;
        this.ageCategoryService = ageCategoryService;
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<?> getCategoriesBySeasonId(@PathVariable Integer id) {
        try
        {
            Set<AgeCategory> categories = seasonService.getCategoriesBySeasonId(id);
            return ResponseEntity.ok(categories.stream().map(ageCategoryService::convertToDto).collect(Collectors.toList()));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/upcoming")
    public List<SeasonDto> getUpcomingSeasons()
    {
        return seasonService.getUpcomingSeasons().stream().map(seasonService::convertToDto).collect(Collectors.toList());
    }

    @GetMapping
    public List<SeasonDto> getAllSeasons()
    {
        return seasonService.getAllSeasons().stream().map(seasonService::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeasonById(@PathVariable int id)
    {
        try
        {
            Season season = seasonService.getSeasonById(id);
            return ResponseEntity.ok(seasonService.convertToDto(season));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<SeasonDto> createSeason(@RequestBody SeasonDto seasonDto)
    {
        Season season = seasonService.createSeason(seasonService.convertToEntity(seasonDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(seasonService.convertToDto(season));
    }
}