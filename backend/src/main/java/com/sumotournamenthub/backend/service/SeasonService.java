package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.SeasonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeasonService
{
    private final AgeCategoryService ageCategoryService;

    public SeasonDto convertToDto(Season season)
    {
        Set<AgeCategoryDto> DtoCategories = new HashSet<>();

        for (AgeCategory ageCategory : season.getCategories())
        {
            DtoCategories.add(ageCategoryService.convertToDto(ageCategory));
        }

        return SeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .categories(DtoCategories)
                .build();
    }

    public Season convertToEntity(SeasonDto seasonDto)
    {
        Set<AgeCategory> categories = new HashSet<>();

        for (AgeCategoryDto ageCategoryDto : seasonDto.getCategories())
        {
            categories.add(ageCategoryService.convertToEntity(ageCategoryDto));
        }

        Season season = new Season(seasonDto.getName(), seasonDto.getStartDate(), seasonDto.getEndDate());
        season.setId(season.getId());
        season.setCategories(categories);

        return season;
    }
}
