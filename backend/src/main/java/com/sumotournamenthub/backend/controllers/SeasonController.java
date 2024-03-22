package com.sumotournamenthub.backend.controllers;

import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import com.sumotournamenthub.backend.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seasons")
public class SeasonController
{
    @Autowired
    private SeasonRepository seasonRepository;

    private SeasonService seasonService;

    @GetMapping
    public List<SeasonDto> getAllSeasons()
    {
        return seasonRepository.findAll().stream().map(season -> seasonService.convertToDto(season)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDto> getSeasonById(@PathVariable int id)
    {
        Optional<Season> season = seasonRepository.findById(id);

        if (season.isPresent())
        {
            return ResponseEntity.ok(seasonService.convertToDto(season.get()));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeasonDto> updateSeason(@PathVariable int id, @RequestBody SeasonDto seasonDto)
    {
        Optional<Season> season = seasonRepository.findById(id);

        if (season.isPresent())
        {
            Season seasonToSave = seasonService.convertToEntity(seasonDto);
            seasonToSave.setId(id);

            Season saved = seasonRepository.save(seasonToSave);

            return ResponseEntity.ok(seasonService.convertToDto(saved));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SeasonDto> createSeason(@RequestBody SeasonDto seasonDto)
    {
        Season seasonToSave = seasonService.convertToEntity(seasonDto);

        Season saved = seasonRepository.save(seasonToSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(seasonService.convertToDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable int id)
    {
        Optional<Season> season = seasonRepository.findById(id);

        if (season.isPresent())
        {
            seasonRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
