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

    @GetMapping("/{id}/categories")
    public ResponseEntity<Set<AgeCategoryDto>> getCategoriesBySeasonId(@PathVariable Integer id) {
        Optional<Set<AgeCategoryDto>> categoryDtos = seasonService.getCategoriesBySeasonId(id);

        if (categoryDtos.isPresent())
        {
            return ResponseEntity.ok(categoryDtos.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/upcoming")
    public List<SeasonDto> getUpcomingSeasons()
    {
        return seasonService.getUpcomingSeasons();
    }

    @GetMapping
    public List<SeasonDto> getAllSeasons()
    {
        return seasonService.getAllSeasons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDto> getSeasonById(@PathVariable int id)
    {
        Optional<SeasonDto> seasonDto = seasonService.getSeasonById(id);

        if (seasonDto.isPresent())
        {
            return ResponseEntity.ok(seasonDto.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeasonDto> updateSeason(@PathVariable int id, @RequestBody SeasonDto seasonDto)
    {
        Optional<SeasonDto> seasonResponse = seasonService.updateSeason(id, seasonDto);

        if (seasonResponse.isPresent())
        {
            return ResponseEntity.ok(seasonResponse.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SeasonDto> createSeason(@RequestBody SeasonDto seasonDto)
    {
        SeasonDto seasonResponse = seasonService.createSeason(seasonDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(seasonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable int id)
    {
        if (seasonService.deleteSeason(id))
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}