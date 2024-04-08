package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.repository.CompetitionRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;
    private final SeasonService seasonService;

    public List<CompetitionDto> getAllCompetitions() {
        return repository.findAll().stream().map(CompetitionDto::from).toList();
    }

    public CompetitionDto getCompetition(int id) {
        return CompetitionDto.from(getCompetitionEntity(id));
    }

    public Competition getCompetitionEntity(int id) {
        return repository.findById(id).orElseThrow(() -> ExceptionUtils.entityNotFound("Competition", id));
    }

    public CompetitionDto createCompetition(CompetitionDto dto) {
        var season = seasonService.getSeasonEntity(dto.getSeasonId());
        var competition = new Competition(dto.getName(), season, dto.getStartTime(), dto.getEndTime(), dto.getRank());
        return CompetitionDto.from(repository.save(competition));
    }

    public List<AgeCategoryDto> getAllAgeCategories(int competitionId) {
        var seasonId = getCompetitionEntity(competitionId).getSeason().getId();
        return seasonService.getAllAgeCategories(seasonId);
    }

    public void deleteCompetition(Competition competition) {
        repository.delete(competition);
    }
}