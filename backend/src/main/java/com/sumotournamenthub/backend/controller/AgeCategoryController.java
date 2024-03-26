package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.service.AgeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sumotournamenthub.backend.repository.AgeCategoryRepository;
import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import static com.sumotournamenthub.backend.utils.ExceptionUtils.notExist;
import static java.lang.String.format;
import java.util.List;

@RestController
@RequestMapping("/agecategories")
public class AgeCategoryController {

    @Autowired
    private AgeCategoryRepository ageCategoryRepository;
    @Autowired
    private AgeCategoryService ageCategoryService;

    @GetMapping
    public List<AgeCategory> getAllCategories() {
        return ageCategoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<AgeCategory> getCategoryById(@PathVariable Integer id) {
        var category = ageCategoryRepository.findById(id).
                orElseThrow(() -> notExist(format("Age category with id %d does not exist", id)));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AgeCategoryDto> createCategory(@RequestBody AgeCategoryDto categoryDto) {
        AgeCategoryDto savedCategory = ageCategoryService.createAgeCategory(categoryDto);
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