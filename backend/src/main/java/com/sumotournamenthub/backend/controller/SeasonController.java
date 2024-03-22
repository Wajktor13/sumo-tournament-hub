package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import com.sumotournamenthub.backend.service.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.sumotournamenthub.backend.utils.ExceptionUtils.notExist;
import static java.lang.String.format;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

    private final SeasonRepository repository;

    private SeasonService seasonService;

    public SeasonController(SeasonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{seasonId}/categories")
    public ResponseEntity<Set<AgeCategory>> getCategoriesBySeasonId(@PathVariable Integer id) {
        var season = repository.findById(id).orElseThrow(() -> notExist(format("Season with %d id does not exist", id)));
        return ResponseEntity.ok(season.getCategories());
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
