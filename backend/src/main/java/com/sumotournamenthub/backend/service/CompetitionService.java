package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import com.sumotournamenthub.backend.repository.CompetitionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;
    private final SeasonService seasonService;

    public CompetitionDto convertToDto(Competition competition)
    {
        return CompetitionDto.builder()
                .id(competition.getId())
                .name(competition.getName())
                .startTime(competition.getStartTime())
                .endTime(competition.getEndTime())
                .countryLimits(competition.getCountryLimits())
                .seasonId(competition.getSeason().getId())
                .build();
    }

    public Competition convertToEntity(CompetitionDto competitionDto)
    {
        Season season = seasonService.getSeasonById(competitionDto.getSeasonId());

        Competition competition = new Competition();
        competition.setId(competitionDto.getId());
        competition.setName(competitionDto.getName());
        competition.setSeason(season);
        competition.setStartTime(competitionDto.getStartTime());
        competition.setEndTime(competitionDto.getEndTime());
        competition.setCountryLimits(competitionDto.getCountryLimits());

        return competition;
    }

    public List<Competition> getAllCompetitions()
    {
        return repository.findAll();
    }

    public Competition getCompetitionById(int id)
    {
        return findById(id);
    }

    public Competition createCompetition(Competition competition)
    {
        return repository.save(competition);
    }

    private Competition findById(int id)
    {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(format("Competition with id %d does not exist", id)));
    }
}
