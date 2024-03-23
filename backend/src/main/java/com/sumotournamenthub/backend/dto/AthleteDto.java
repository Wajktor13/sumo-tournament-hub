package com.sumotournamenthub.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Value
@Builder
public class AthleteDto {
    int id;
    String firstName;
    String secondName;
    String gender;
    LocalDate birthdate;
    Integer clubId;
}
