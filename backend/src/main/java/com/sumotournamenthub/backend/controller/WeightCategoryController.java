package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.WeightCategory;
import com.sumotournamenthub.backend.repository.WeightCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.sumotournamenthub.backend.utils.ExceptionUtils.notExist;
import static java.lang.String.format;
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

    public ResponseEntity<WeightCategory> getCategoryById(@PathVariable Integer id) {
        var category = weightCategoryRepository.findById(id).
                orElseThrow(() -> notExist(format("Weight category with id %d does not exist", id)));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WeightCategory> createWeightCategory(@RequestBody WeightCategory weightCategory) {
        WeightCategory savedWeightCategory = weightCategoryRepository.save(weightCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWeightCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeightCategory> updateWeightCategory(@PathVariable Integer id, @RequestBody WeightCategory updatedWeightCategory) {
        return weightCategoryRepository.findById(id).map(existingWeightCategory -> {
                updatedWeightCategory.setId(id);
                WeightCategory savedWeightCategory = weightCategoryRepository.save(updatedWeightCategory);
                return ResponseEntity.ok(savedWeightCategory);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWeightCategory(@PathVariable Integer id) {
        return weightCategoryRepository.findById(id).map(existingWeightCategory -> {
            weightCategoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
