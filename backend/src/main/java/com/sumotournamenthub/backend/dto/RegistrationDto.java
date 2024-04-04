package com.sumotournamenthub.backend.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class RegistrationDto {
    Integer id;
    Integer athleteId;
    Integer weightCategoryId;
    LocalDate registrationDate;
}
