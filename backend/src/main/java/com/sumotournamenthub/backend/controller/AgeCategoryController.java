package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.WeightCategoryDto;
import com.sumotournamenthub.backend.service.AgeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import java.util.List;

@RestController
@RequestMapping("/ageCategories")
public class AgeCategoryController {

    @Autowired
    private AgeCategoryService ageCategoryService;

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