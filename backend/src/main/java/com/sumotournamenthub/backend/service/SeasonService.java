package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService
{
    private final AgeCategoryService ageCategoryService;

    private final SeasonRepository repository;

    private SeasonDto convertToDto(Season season)
    {
        return SeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .build();
    }

    private Season convertToEntity(SeasonDto seasonDto)
    {
        Season season = new Season(seasonDto.getName(), seasonDto.getStartDate(), seasonDto.getEndDate());
        season.setId(seasonDto.getId());

        return season;
    }

    public List<SeasonDto> getUpcomingSeasons()
    {
        return repository.findByStartDateGreaterThanEqual(LocalDate.now())
                .stream().map(season -> convertToDto(season)).collect(Collectors.toList());
    }

    public Optional<Set<AgeCategoryDto>> getCategoriesBySeasonId(int id)
    {
        Optional<Season> season = repository.findById(id);

        if (season.isPresent())
        {
            Set<AgeCategoryDto> ageCategoryDtos = new HashSet<>();

            for (AgeCategory ageCategory : season.get().getCategories())
            {
                ageCategoryDtos.add(ageCategoryService.convertToDto(ageCategory));
            }

            return Optional.of(ageCategoryDtos);
        }
        else
        {
            return Optional.empty();
        }
    }

    public List<SeasonDto> getAllSeasons()
    {
        return repository.findAll()
                .stream().map(season -> convertToDto(season)).collect(Collectors.toList());
    }

    public Optional<SeasonDto> getSeasonById(int id)
    {
        Optional<Season> season = repository.findById(id);

        if (season.isPresent())
        {
            return Optional.of(convertToDto(season.get()));
        }
        else
        {
            return Optional.empty();
        }
    }

    public Optional<SeasonDto> updateSeason(int id, SeasonDto seasonDto)
    {
        Optional<Season> season = repository.findById(id);

        if (season.isPresent())
        {
            Season seasonToSave = convertToEntity(seasonDto);

            Season saved = repository.save(seasonToSave);

            return Optional.of(convertToDto(saved));
        }
        else
        {
            return Optional.empty();
        }
    }

    public SeasonDto createSeason(SeasonDto seasonDto)
    {
        Season seasonToSave = convertToEntity(seasonDto);

        Season saved = repository.save(seasonToSave);

        return convertToDto(saved);
    }

    public boolean deleteSeason(int id)
    {
        Optional<Season> season = repository.findById(id);

        if (season.isPresent())
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