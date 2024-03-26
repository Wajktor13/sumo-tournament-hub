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

    //Is this method needed after changes?
    /*public AgeCategory addSeasonAndSaveCategory(AgeCategoryDto dto) {
        var category = createOrRetrieveCategory(dto);
        category.getSeasons().add(dto.getSeason());
        return ageCategoryRepository.save(category);
    }*/

    private AgeCategory createOrRetrieveCategory(AgeCategoryDto dto) {
        return ageCategoryRepository.findByNameAndAgeLowerBoundAndAgeUpperBoundAndGender(
                        dto.getAgeCategoryName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender())
                .orElseGet(() -> new AgeCategory(dto.getAgeCategoryName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender()));
    }

    public AgeCategoryDto createAgeCategory(AgeCategoryDto ageCategoryDto)
    {
        AgeCategory newAgeCategory = convertToEntity(ageCategoryDto);

        AgeCategory created = ageCategoryRepository.save(newAgeCategory);

        return convertToDto(created);
    }

    public AgeCategoryDto convertToDto(AgeCategory ageCategory)
    {
        return AgeCategoryDto.builder()
                .id(ageCategory.getId())
                .ageCategoryName(ageCategory.getName())
                .ageLowerBound(ageCategory.getAgeLowerBound())
                .ageUpperBound(ageCategory.getAgeUpperBound())
                .gender(ageCategory.getGender())
                .build();
    }

    public AgeCategory convertToEntity(AgeCategoryDto ageCategoryDto)
    {
        return createOrRetrieveCategory(ageCategoryDto);
    }
}

