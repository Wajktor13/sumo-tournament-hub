package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Category;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.domain.WeightRange;
import com.sumotournamenthub.backend.dto.CategoryDto;
import com.sumotournamenthub.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category addSeasonAndSaveCategory(Season season, CategoryDto dto, Set<WeightRange> weightRanges) {
        Category category = createOrRetrieveCategory(dto, weightRanges);
        category.getSeasons().add(season);
        return categoryRepository.save(category);
    }

    private Category createOrRetrieveCategory(CategoryDto dto, Set<WeightRange> weightRanges) {
        return categoryRepository.findByNameAndAgeLowerBoundAndAgeUpperBoundAndGender(
                        dto.getName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender())
                .filter(category -> category.getWeightRanges().equals(weightRanges))
                .orElseGet(() -> new Category(dto.getName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender(), weightRanges));
    }

}

