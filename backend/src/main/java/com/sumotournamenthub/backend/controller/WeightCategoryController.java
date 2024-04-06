package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.WeightCategoryDto;
import com.sumotournamenthub.backend.service.WeightCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/weightcategories")
public class WeightCategoryController {

    @Autowired
    private WeightCategoryService service;

    @GetMapping
    public List<WeightCategoryDto> getAllWeightCategories() {
        return service.getAllWeightCategories();
    }

    public ResponseEntity<WeightCategoryDto> getCategoryById(@PathVariable Integer id) {
        var category = service.getWeightCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WeightCategoryDto> createWeightCategory(@RequestBody WeightCategoryDto dto) {
        var savedWeightCategory = service.createWeightCategory(dto);
        return new ResponseEntity<>(savedWeightCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeightCategory(@PathVariable Integer id) {
        service.deleteWeightCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
