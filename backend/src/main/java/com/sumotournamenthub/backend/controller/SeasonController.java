package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.service.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping
    public List<SeasonDto> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDto> getSeason(@PathVariable int id) {
        var season = seasonService.getSeason(id);
        return new ResponseEntity<>(season, HttpStatus.OK);
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<List<AgeCategoryDto>> getAllAgeCategories(@PathVariable Integer id) {
        var ageCategories = seasonService.getAllAgeCategories(id);
        return new ResponseEntity<>(ageCategories, HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public List<SeasonDto> getUpcomingSeasons() {
        return seasonService.getUpcomingSeasons();
    }

    @PutMapping("/{id}/addAgeCategory/{categoryId}")
    public ResponseEntity<Void> addAgeCategoryToSeason(@PathVariable Integer id, @PathVariable Integer categoryId) {
        seasonService.addAgeCategoryToSeason(id, categoryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<SeasonDto> createSeason(@RequestBody SeasonDto seasonDto) {
        var season = seasonService.createSeason(seasonDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(season);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable int id) {
        seasonService.deleteSeason(id);
        return ResponseEntity.noContent().build();
    }
}