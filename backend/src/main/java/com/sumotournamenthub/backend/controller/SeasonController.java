package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.service.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping
    public List<SeasonDto> getAllSeasons()
    {
        return seasonService.getAllSeasons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDto> getSeasonById(@PathVariable int id) {
        return seasonService.getSeasonById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<Set<AgeCategoryDto>> getCategoriesBySeasonId(@PathVariable Integer id) {
        Optional<Set<AgeCategoryDto>> categoryDtos = seasonService.getCategoriesBySeasonId(id);
        return categoryDtos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/upcoming")
    public List<SeasonDto> getUpcomingSeasons()
    {
        return seasonService.getUpcomingSeasons();
    }

    @PutMapping("/{id}/addAgeCategory/{categoryId}")
    public ResponseEntity<Void> addAgeCategoryToSeason(@PathVariable Integer id, @PathVariable Integer categoryId) {
        seasonService.addAgeCategoryToSeason(id, categoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeasonDto> updateSeason(@PathVariable int id, @RequestBody SeasonDto seasonDto) {
        return seasonService.updateSeason(id, seasonDto)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SeasonDto> createSeason(@RequestBody SeasonDto seasonDto) {
        SeasonDto seasonResponse = seasonService.createSeason(seasonDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(seasonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable int id) {
        return seasonService.deleteSeason(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}