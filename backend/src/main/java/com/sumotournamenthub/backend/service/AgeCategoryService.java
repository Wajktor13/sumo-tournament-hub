package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.repository.AgeCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgeCategoryService {
    private final AgeCategoryRepository ageCategoryRepository;

    public AgeCategory addSeasonAndSaveCategory(Season season, AgeCategoryDto dto) {
        AgeCategory category = createOrRetrieveCategory(dto);
        category.getSeasons().add(season);
        return ageCategoryRepository.save(category);
    }

    private AgeCategory createOrRetrieveCategory(AgeCategoryDto dto) {
        return ageCategoryRepository.findByNameAndAgeLowerBoundAndAgeUpperBoundAndGender(
                        dto.getName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender())
                .orElseGet(() -> new AgeCategory(dto.getName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender()));
    }

    public AgeCategoryDto convertToDto(AgeCategory ageCategory)
    {
        return AgeCategoryDto.builder()
                .id(ageCategory.getId())
                .name(ageCategory.getName())
                .ageLowerBound(ageCategory.getAgeLowerBound())
                .ageUpperBound(ageCategory.getAgeUpperBound())
                .gender(ageCategory.getGender())
                .build();
    }

    public AgeCategory convertToEntity(AgeCategoryDto ageCategoryDto)
    {
        AgeCategory ageCategory = new AgeCategory(ageCategoryDto.getName(), ageCategoryDto.getAgeLowerBound(), ageCategoryDto.getAgeUpperBound(), ageCategoryDto.getGender());
        ageCategory.setId(ageCategory.getId());

        return ageCategory;
    }
}

