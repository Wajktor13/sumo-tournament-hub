package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import com.sumotournamenthub.backend.repository.CompetitionRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;

    public List<CompetitionDto> getAllCompetitions() {
        return repository.findAll().stream().map(CompetitionDto::from).toList();
    }

    public CompetitionDto getCompetition(int id) {
        return CompetitionDto.from(getCompetitionEntity(id));
    }

    public Competition getCompetitionEntity(int id) {
        return repository.findById(id).orElseThrow(() -> ExceptionUtils.entityNotFound("Competition", id));
    }

    public Competition addCompetition(Competition competition) {
        return repository.save(competition);
    }

    public void deleteCompetition(Competition competition) {
        repository.delete(competition);
    }
}
