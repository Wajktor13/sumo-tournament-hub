package com.sumotournamenthub.backend.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WeightCategoryDto
{
    int id;
    int weightUpperLimit;
    int competitionId;
    int ageCategoryId;
}
