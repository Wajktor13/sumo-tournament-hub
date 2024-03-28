package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.AgeCategory;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeasonService
{
    private final SeasonRepository repository;

    public SeasonDto convertToDto(Season season) {
        return SeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .build();
    }

    public Season convertToEntity(SeasonDto seasonDto) {
        Season season = new Season(seasonDto.getName(), seasonDto.getStartDate(), seasonDto.getEndDate());
        season.setId(seasonDto.getId());

        return season;
    }

    public List<Season> getUpcomingSeasons() {
        return repository.findByStartDateGreaterThanEqual(LocalDate.now());
    }

    public Set<AgeCategory> getCategoriesBySeasonId(int id) {
        Season season = findById(id);

        return new HashSet<>(season.getCategories());
    }

    public List<Season> getAllSeasons() {
        return repository.findAll();
    }

    public Season getSeasonById(int id) {
        return findById(id);
    }

    public Season createSeason(Season season) {
        return repository.save(season);
    }

    private Season findById(int id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Season with id %d does not exist", id)));
    }
}