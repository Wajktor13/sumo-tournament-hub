package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.WeightCategoryDto;
import com.sumotournamenthub.backend.repository.AgeCategoryRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgeCategoryService {
    private final AgeCategoryRepository repository;
    private final AthleteService athleteService;
    private final SeasonService seasonService;

    public AgeCategoryDto createAgeCategory(AgeCategoryDto ageCategoryDto) {
        AgeCategory newAgeCategory = createOrRetrieveCategory(ageCategoryDto);
        return convertToDto(repository.save(newAgeCategory));
    }

    public AgeCategoryDto getAgeCategory(int id) {
        return convertToDto(getAgeCategoryEntity(id));
    }

    public AgeCategory getAgeCategoryEntity(int id) {
        return repository.findById(id)
                .orElseThrow(() -> ExceptionUtils.entityNotFound("Weight Category", id));
    }

    public List<AgeCategoryDto> getAllAgeCategories() {
        return repository.findAll().stream().map(this::convertToDto).toList();
    }

    public List<WeightCategoryDto> getAllWeightCategories(int id) {
        var ageCategory = getAgeCategoryEntity(id);
        return ageCategory.getWeightCategories().stream().map(WeightCategoryService::convertToDto).toList();
    }

    @Transactional
    public void addSeasonToAgeCategory(AgeCategory ageCategory, Season season) {
        ageCategory.getSeasons().add(season);
        repository.save(ageCategory);
    }

    public void deleteAgeCategory(int id) {
        repository.delete(getAgeCategoryEntity(id));
    }

    private AgeCategory createOrRetrieveCategory(AgeCategoryDto dto) {
        return repository.findByNameAndAgeLowerBoundAndAgeUpperBoundAndGender(
                        dto.getAgeCategoryName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender())
                .orElseGet(() -> new AgeCategory(dto.getAgeCategoryName(), dto.getAgeLowerBound(), dto.getAgeUpperBound(), dto.getGender()));
    }

    public AgeCategoryDto getAgeCategoryByAthleteIdAndSeasonId(Integer athleteId, Integer seasonId) {
        var athleteAgeCategories = athleteService.getAgeCategoryByAthleteId(athleteId);
        var seasonAgeCategories = seasonService.getAllAgeCategories(seasonId);
        return seasonAgeCategories.stream()
                .filter(athleteAgeCategories::contains)
                .findFirst()
                .orElse(null);
    }

    public AgeCategoryDto convertToDto(AgeCategory ageCategory) {
        return AgeCategoryDto.builder()
                .id(ageCategory.getId())
                .ageCategoryName(ageCategory.getName())
                .ageLowerBound(ageCategory.getAgeLowerBound())
                .ageUpperBound(ageCategory.getAgeUpperBound())
                .gender(ageCategory.getGender())
                .build();
    }
}