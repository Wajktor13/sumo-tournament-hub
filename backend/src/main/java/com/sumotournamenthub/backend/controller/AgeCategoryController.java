package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.WeightCategoryDto;
import com.sumotournamenthub.backend.service.AgeCategoryService;
import com.sumotournamenthub.backend.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ageCategories")
public class AgeCategoryController {

    private final AgeCategoryService ageCategoryService;
    private final SeasonService seasonService;

    @Autowired
    public AgeCategoryController(AgeCategoryService ageCategoryService, SeasonService seasonService) {
        this.ageCategoryService = ageCategoryService;
        this.seasonService = seasonService;
    }

    @GetMapping
    public List<AgeCategoryDto> getAllCategories() {
        return ageCategoryService.getAllAgeCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgeCategoryDto> getCategoryById(@PathVariable Integer id) {
        var category = ageCategoryService.getAgeCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}/weightCategories")
    public ResponseEntity<List<WeightCategoryDto>> getAllWeightCategories(@PathVariable Integer id) {
        var weightCategories = ageCategoryService.getAllWeightCategories(id);
        return new ResponseEntity<>(weightCategories, HttpStatus.OK);
    }

    @GetMapping("/athletes/{athleteId}/seasons/{seasonId}")
    public ResponseEntity<AgeCategoryDto> getAthleteAgeCategoriesInSeason
            (@PathVariable Integer athleteId, @PathVariable Integer seasonId) {
        var ageCategory = seasonService.getAthleteAgeCategoriesInGivenSeason(seasonId, athleteId);
        return new ResponseEntity<>(ageCategory, HttpStatus.OK);
    }

    @GetMapping("/byAthletesAndSeason")
    public ResponseEntity<AgeCategoryDto> getAthleteAgeCategoriesInSeason(
            @RequestParam("athleteIds") String athleteIds,
            @RequestParam("seasonId") Integer seasonId) {

        var athleteIdList = Arrays.stream(athleteIds.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        var ageCategory = seasonService.getCommonAgeCategoryForAthletesInGivenSeason(seasonId, athleteIdList);

        return new ResponseEntity<>(ageCategory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AgeCategoryDto> createCategory(@RequestBody AgeCategoryDto categoryDto) {
        var savedCategory = ageCategoryService.createAgeCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        ageCategoryService.deleteAgeCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}