package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.SeasonDto;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final AgeCategoryService ageCategoryService;

    private final SeasonRepository repository;

    public List<SeasonDto> getUpcomingSeasons() {
        return repository.findByStartDateGreaterThanEqual(LocalDate.now())
                .stream().map(this::convertToDto).toList();
    }

    public Optional<Set<AgeCategoryDto>> getCategoriesBySeasonId(int id) {
        return repository.findById(id)
                .map(season -> season.getCategories().stream()
                        .map(ageCategoryService::convertToDto)
                        .collect(Collectors.toSet()));
    }

    public List<SeasonDto> getAllSeasons() {
        return repository.findAll()
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<SeasonDto> getSeasonById(int id) {
        Optional<Season> season = repository.findById(id);

        return season.map(this::convertToDto);
    }

    public Optional<SeasonDto> updateSeason(int id, SeasonDto seasonDto) {
        return repository.findById(id)
            .map(existingSeason -> {
                var saved = repository.save(convertToEntity(seasonDto));
                return Optional.of(convertToDto(saved));
            }).orElse(Optional.empty());
    }

    public SeasonDto createSeason(SeasonDto seasonDto) {
        Season seasonToSave = convertToEntity(seasonDto);

        Season saved = repository.save(seasonToSave);

        return convertToDto(saved);
    }

    public boolean deleteSeason(int id) {
        return repository.findById(id)
            .map(s -> {
                repository.deleteById(id);
                return true;
            }).orElse(false);

    }

    private SeasonDto convertToDto(Season season) {
        return SeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .build();
    }

    private Season convertToEntity(SeasonDto seasonDto) {
        Season season = new Season(seasonDto.getName(), seasonDto.getStartDate(), seasonDto.getEndDate());
        season.setId(seasonDto.getId());
        return season;
    }
}