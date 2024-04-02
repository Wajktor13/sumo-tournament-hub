package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.repository.CompetitionRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;

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

    public Set<AgeCategory> getAllAgeCategoriesByCompetitionId(int competitionId) {
        return getCompetitionEntity(competitionId).getSeason().getCategories();
    }
}