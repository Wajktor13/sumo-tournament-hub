package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Registration;
import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.dto.RegistrationDto;
import com.sumotournamenthub.backend.repository.RegistrationRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository repository;
    private final AthleteService athleteService;
    private final WeightCategoryService weightCategoryService;
    private final SeasonService seasonService;

    public RegistrationDto registerAthleteForWeightCategory(RegistrationDto dto) {
        ensureSingleRegistrationPerCategory(dto.getAthleteId(), dto.getWeightCategoryId());
        var athlete = athleteService.getAthleteEntity(dto.getAthleteId());
        var weightCategory = weightCategoryService.getWeightCategoryEntity(dto.getWeightCategoryId());

        var registration = new Registration(athlete, weightCategory);
        registration.setRegistrationDate(dto.getRegistrationDate());

        return convertToDto(registration);
    }

    public List<AgeCategoryDto> getAgeCategoryByRegistrationIdAndAthleteId(int athleteId) {
        var athlete = athleteService.getAthleteEntity(athleteId);
        Set<Registration> registrations = athlete.getRegistrations();
        return registrations.stream()
                .map(registration -> weightCategoryService.getAgeCategoryByWeightCategoryId(registration.getWeightCategory().getId()))
                .toList();
    }

    public AgeCategoryDto getAgeCategoryByAthleteIdAndSeasonId(Integer athleteId, Integer seasonId) {
        List<AgeCategoryDto> ageCategoryDtoList = getAgeCategoryByRegistrationIdAndAthleteId(athleteId);
        var seasonAgeCategories = seasonService.getAllAgeCategories(seasonId);
        return seasonAgeCategories.stream()
                .filter(ageCategoryDtoList::contains)
                .findFirst()
                .orElse(null);
    }

    public static RegistrationDto convertToDto(Registration registration){
        return RegistrationDto.builder()
                .id(registration.getId())
                .weightCategoryId(registration.getWeightCategory().getId())
                .athleteId(registration.getAthlete().getId())
                .registrationDate(registration.getRegistrationDate())
                .build();
    }

    private void ensureSingleRegistrationPerCategory(int athleteId, int weightCategoryId){
        var exists = repository.existsByAthleteIdAndWeightCategoryId(athleteId, weightCategoryId);
        if (exists) {
            throw new IllegalStateException("Athlete is already registered for this weight category");
        }
    }

}
