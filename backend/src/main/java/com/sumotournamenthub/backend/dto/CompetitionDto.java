package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.Country;
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
}
