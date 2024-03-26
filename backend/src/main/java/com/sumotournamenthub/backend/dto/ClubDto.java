package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.Country;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClubDto {
    int id;
    String name;
    Country country;
}
