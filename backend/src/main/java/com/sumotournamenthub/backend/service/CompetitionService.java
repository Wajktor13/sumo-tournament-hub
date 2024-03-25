package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Competition;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import com.sumotournamenthub.backend.repository.CompetitionRepository;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;
    private final SeasonRepository seasonRepository;

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

    public Optional<Competition> convertToEntity(CompetitionDto competitionDto)
    {
        Optional<Season> season = seasonRepository.findById(competitionDto.getSeasonId());

        if (season.isPresent())
        {
            Competition competition = new Competition();
            competition.setId(competitionDto.getId());
            competition.setName(competitionDto.getName());
            competition.setSeason(season.get());
            competition.setStartTime(competitionDto.getStartTime());
            competition.setEndTime(competitionDto.getEndTime());
            competition.setCountryLimits(competitionDto.getCountryLimits());

            return Optional.of(competition);
        }
        else
        {
            return Optional.empty();
        }
    }

    public List<CompetitionDto> getAllCompetitions()
    {
        return repository.findAll()
                .stream().map(competition -> convertToDto(competition)).collect(Collectors.toList());
    }

    public Optional<CompetitionDto> getCompetitionById(int id)
    {
        Optional<Competition> competition = repository.findById(id);

        if (competition.isPresent())
        {
            return Optional.of(convertToDto(competition.get()));
        }
        else
        {
            return Optional.empty();
        }
    }

    public Optional<CompetitionDto> createCompetition(CompetitionDto competitionDto)
    {
        Optional<Competition> competitionToSave = convertToEntity(competitionDto);

        if (competitionToSave.isPresent())
        {
            Competition saved = repository.save(competitionToSave.get());

            return Optional.of(convertToDto(saved));
        }
        else
        {
            return Optional.empty();
        }
    }

    public boolean deleteCompetition(int id)
    {
        Optional<Competition> competition = repository.findById(id);

        if (competition.isPresent())
        {
            repository.deleteById(id);

            return true;
        }
        else
        {
            return false;
        }
    }
}
