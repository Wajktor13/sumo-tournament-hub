package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.WeightCategory;
import com.sumotournamenthub.backend.dto.WeightCategoryDto;
import com.sumotournamenthub.backend.repository.WeightCategoryRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeightCategoryService {

    private final WeightCategoryRepository repository;
    private final AgeCategoryService ageCategoryService;
    private final CompetitionService competitionService;

    public WeightCategoryDto getWeightCategory(int id) {
        return convertToDto(getWeightCategoryEntity(id));
    }

    public WeightCategory getWeightCategoryEntity(int id) {
        return repository.findById(id)
                .orElseThrow(() -> ExceptionUtils.entityNotFound("Weight Category", id));
    }

    public List<WeightCategoryDto> getAllWeightCategories() {
        return repository.findAll().stream().map(this::convertToDto).toList();
    }

    public WeightCategoryDto createWeightCategory(WeightCategoryDto dto) {
        var ageCategory = ageCategoryService.getAgeCategoryEntity(dto.getAgeCategoryId());
        var competition = competitionService.getCompetitionEntity(dto.getCompetitionId());

        var weightCategory = new WeightCategory(ageCategory, competition, dto.getWeightUpperLimit());
        return convertToDto(repository.save(weightCategory));
    }

    public void deleteWeightCategory(int id) {
        repository.delete(getWeightCategoryEntity(id));
    }

    public WeightCategoryDto convertToDto(WeightCategory category) {
        return WeightCategoryDto.builder()
                .id(category.getId())
                .weightUpperLimit(category.getWeightUpperLimit())
                .openWeight(category.isOpenWeight())
                .ageCategoryId(category.getAgeCategory().getId())
                .competitionId(category.getCompetition().getId())
                .build();
    }

}
