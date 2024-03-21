package com.sumotournamenthub.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sumotournamenthub.backend.repository.AgeCategoryRepository;
import com.sumotournamenthub.backend.domain.AgeCategory;

import java.util.List;

@RestController
@RequestMapping("/agecategories")
public class AgeCategoryController {

    @Autowired
    private AgeCategoryRepository ageCategoryRepository;

    @GetMapping
    public List<AgeCategory> getAllCategories() {
        return ageCategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgeCategory> getCategoryById(@PathVariable Integer id) {
        AgeCategory category = ageCategoryRepository.findById(id).orElse(null);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AgeCategory> createCategory(@RequestBody AgeCategory category) {
        AgeCategory savedCategory = ageCategoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgeCategory> updateCategory(@PathVariable Integer id, @RequestBody AgeCategory updatedCategory) {
        AgeCategory category = ageCategoryRepository.findById(id).orElse(null);
        if (category != null) {
            updatedCategory.setId(id);
            AgeCategory savedCategory = ageCategoryRepository.save(updatedCategory);
            return ResponseEntity.ok(savedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        AgeCategory category = ageCategoryRepository.findById(id).orElse(null);
        if (category != null) {
            ageCategoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}