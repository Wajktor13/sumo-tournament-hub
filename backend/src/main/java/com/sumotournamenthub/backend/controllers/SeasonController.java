package com.sumotournamenthub.backend.controllers;

import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seasons")
public class SeasonController
{
    @Autowired
    private SeasonRepository seasonRepository;

    @GetMapping
    public List<Season> getAllSeasons()
    {
        return seasonRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable int id)
    {
        Optional<Season> season = seasonRepository.findById(id);

        if (season.isPresent())
        {
            return ResponseEntity.ok(season.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Season> updateSeason(@PathVariable int id, @RequestBody Season updatedSeason)
    {
        Optional<Season> season = seasonRepository.findById(id);

        if (season.isPresent())
        {
            updatedSeason.setId(id);
            Season saved = seasonRepository.save(updatedSeason);

            return ResponseEntity.ok(saved);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Season> createSeason(@RequestBody Season season)
    {
        Season saved = seasonRepository.save(season);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
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
