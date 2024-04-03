package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.repository.CompetitionRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;

    public List<Competition> getAllCompetitions()
    {
        return repository.findAll();
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
}
