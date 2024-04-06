package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.Country;
import com.sumotournamenthub.backend.domain.Competition;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Map;

@Value
@Builder
public class CompetitionDto {
    int id;
    String name;
    LocalDate startTime;
    LocalDate endTime;
    Map<Country, Integer> countryLimits;
    int seasonId;

    public static CompetitionDto from(Competition competition) {
        return CompetitionDto.builder()
                .id(competition.getId())
                .name(competition.getName())
                .startTime(competition.getStartTime())
                .endTime(competition.getEndTime())
                .countryLimits(competition.getCountryLimits())
                .seasonId(competition.getSeason().getId())
                .build();
    }
}
