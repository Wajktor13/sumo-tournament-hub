package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.AgeCategoryName;
import com.sumotournamenthub.backend.constants.Gender;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AgeCategoryDto {
    Integer id;
    AgeCategoryName ageCategoryName;
    Integer ageLowerBound;
    Integer ageUpperBound;
    Gender gender;
    boolean openWeightAvailable;
}
