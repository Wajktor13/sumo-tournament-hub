package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.repository.AgeCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgeCategoryService {
    private final AgeCategoryRepository ageCategoryRepository;

    public AgeCategory addSeasonAndSaveCategory(AgeCategoryDto dto) {
        var category = createOrRetrieveCategory(dto);
        category.getSeasons().add(dto.getSeason());
        return ageCategoryRepository.save(category);
    }

    private AgeCategory createOrRetrieveCategory(AgeCategoryDto dto) {
        return ageCategoryRepository.findByNameAndAgeLowerBoundAndAgeUpperBoundAndGender(
                        dto.getName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender())
                .orElseGet(() -> new AgeCategory(dto.getName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender()));
    }

}

