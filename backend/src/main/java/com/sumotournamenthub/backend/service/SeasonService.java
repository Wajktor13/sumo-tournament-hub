package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.sumotournamenthub.backend.utils.ExceptionUtils.entityNotFound;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final AgeCategoryService ageCategoryService;
    private final SeasonRepository repository;
    private final AthleteService athleteService;

    public List<SeasonDto> getUpcomingSeasons() {
        return repository.findByStartDateGreaterThanEqual(LocalDate.now())
                .stream().map(this::convertToDto).toList();
    }

    public List<AgeCategoryDto> getAllAgeCategories(int id) {
        var season = getSeasonEntity(id);
        return season.getCategories().stream().map(ageCategoryService::convertToDto).toList();
    }

    public AgeCategoryDto getAthleteAgeCategoriesInGivenSeason(int seasonId, int athleteId) {
        var athleteAge = athleteService.calculateAthleteAge(athleteId);
        var season = getSeasonEntity(seasonId);
        return season.getCategories().stream()
                .filter(c-> c.getAgeLowerBound() <= athleteAge && c.getAgeUpperBound() > athleteAge)
                .findFirst()
                .map(ageCategoryService::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException
                        (String.format("Age category for athlete %d in season %d not found", athleteId, seasonId)));
    }

    public List<SeasonDto> getAllSeasons() {
        return repository.findAll().stream().map(this::convertToDto).toList();
    }

    public SeasonDto getSeason(int id) {
        return convertToDto(getSeasonEntity(id));
    }

    public Season getSeasonEntity(int id) {
        return repository.findById(id).orElseThrow(() -> entityNotFound("Season", id));
    }

    public SeasonDto createSeason(SeasonDto seasonDto) {
        var season = new Season(seasonDto.getName(), seasonDto.getStartDate(), seasonDto.getEndDate());

        return convertToDto(repository.save(season));
    }

    public void deleteSeason(int id) {
        var season = getSeasonEntity(id);
        repository.delete(season);
    }

    @Transactional
    public void addAgeCategoryToSeason(int seasonId, int ageCategoryId) {
        Season season = getSeasonEntity(seasonId);
        AgeCategory ageCategory = ageCategoryService.getAgeCategoryEntity(ageCategoryId);
        season.getCategories().add(ageCategory);
        repository.save(season);
        ageCategoryService.addSeasonToAgeCategory(ageCategory, season);
    }

    private SeasonDto convertToDto(Season season) {
        return SeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .build();
    }

}