package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.repository.CompetitionRepository;

import java.util.List;
import java.util.Optional;

public class CompetitionService {
    private CompetitionRepository repository;

    public CompetitionService(CompetitionRepository repository)
    {
        this.repository = repository;
    }

    public List<Competition> getCompetitions()
    {
        return repository.findAll();
    }

    public Optional<Competition> getCompetitionById(int id)
    {
        return repository.findById(id);
    }

    public void addCompetition(Competition competition)
    {
        repository.save(competition);
    }

    public void deleteCompetition(Competition competition)
    {
        repository.delete(competition);
    }
}
