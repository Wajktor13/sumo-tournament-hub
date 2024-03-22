package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.sumotournamenthub.backend.utils.ExceptionUtils.notExist;
import static java.lang.String.format;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

    private final SeasonRepository repository;

    public SeasonController(SeasonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{seasonId}/categories")
    public ResponseEntity<Set<AgeCategory>> getCategoriesBySeasonId(@PathVariable Integer id) {
        var season = repository.findById(id).orElseThrow(() -> notExist(format("Season with %d id does not exist", id)));
        return ResponseEntity.ok(season.getCategories());
    }

}
