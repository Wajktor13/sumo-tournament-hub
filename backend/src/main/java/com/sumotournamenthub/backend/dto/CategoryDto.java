package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.Gender;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryDto {
    Integer id;
    String name;
    Integer ageLowerBound;
    Integer ageUpperBound;
    Gender gender;
}
