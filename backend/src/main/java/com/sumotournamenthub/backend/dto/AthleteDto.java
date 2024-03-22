package com.sumotournamenthub.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Value
@Builder
public class AthleteDto {

    String firstName;
    String lastName;
    Integer clubId;
    String gender;
    LocalDate birthdate;

}
