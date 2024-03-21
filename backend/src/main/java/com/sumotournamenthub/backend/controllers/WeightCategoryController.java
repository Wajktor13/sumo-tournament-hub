package com.sumotournamenthub.backend.controllers;

import com.sumotournamenthub.backend.domain.WeightCategory;
import com.sumotournamenthub.backend.repository.WeightCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weightcategories")
public class WeightCategoryController {

    @Autowired
    private WeightCategoryRepository weightCategoryRepository;

    @GetMapping
    public List<WeightCategory> getAllWeightCategories() {
        return weightCategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeightCategory> getWeightCategoryById(@PathVariable Integer id) {
        WeightCategory weightCategory = weightCategoryRepository.findById(id).orElse(null);
        if (weightCategory != null) {
            return ResponseEntity.ok(weightCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<WeightCategory> createWeightCategory(@RequestBody WeightCategory weightCategory) {
        WeightCategory savedWeightCategory = weightCategoryRepository.save(weightCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWeightCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeightCategory> updateWeightCategory(@PathVariable Integer id, @RequestBody WeightCategory updatedWeightCategory) {
        WeightCategory weightCategory = weightCategoryRepository.findById(id).orElse(null);
        if (weightCategory != null) {
            updatedWeightCategory.setId(id);
            WeightCategory savedWeightCategory = weightCategoryRepository.save(updatedWeightCategory);
            return ResponseEntity.ok(savedWeightCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeightCategory(@PathVariable Integer id) {
        WeightCategory weightCategory = weightCategoryRepository.findById(id).orElse(null);
        if (weightCategory != null) {
            weightCategoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
