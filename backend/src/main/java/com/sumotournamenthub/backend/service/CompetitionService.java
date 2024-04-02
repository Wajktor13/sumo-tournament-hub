package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.repository.CompetitionRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;
    @Autowired
    private AgeCategoryService ageCategoryService;

    public List<Competition> getCompetitions()
    {
        return repository.findAll();
    }

    public Optional<Competition> getCompetitionById(int id)
    {
        return repository.findById(id);
    }

    public Competition getCompetitionEntity(int id) {
        return repository.findById(id).orElseThrow(() -> ExceptionUtils.entityNotFound("Competition", id));
    }

    public Competition addCompetition(Competition competition)
    {
        return repository.save(competition);
    }

    public void deleteCompetition(Competition competition)
    {
        repository.delete(competition);
    }

    public List<AgeCategoryDto> getAllAgeCategoriesByCompetitionId(int competitionId) {
        return getCompetitionEntity(competitionId).getSeason().getCategories().stream()
                .map(ageCategoryService::convertToDto).toList();
    }
}