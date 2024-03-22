package com.sumotournamenthub.backend.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Value
@Builder
public class SeasonDto
{
    int id;

    String name;

    LocalDate startDate;

    LocalDate endDate;

    Set<AgeCategoryDto> categories;
}
