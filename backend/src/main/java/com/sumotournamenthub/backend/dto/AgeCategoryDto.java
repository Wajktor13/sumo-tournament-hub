package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.AgeCategoryName;
import com.sumotournamenthub.backend.constants.Gender;
import com.sumotournamenthub.backend.domain.Season;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AgeCategoryDto {
    Integer id;
    AgeCategoryName name;
    Integer ageLowerBound;
    Integer ageUpperBound;
    Gender gender;
    Season season;
}
