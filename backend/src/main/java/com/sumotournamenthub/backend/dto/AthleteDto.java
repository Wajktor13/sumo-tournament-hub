package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.Gender;
import lombok.*;
import java.time.LocalDate;

@Value
@Builder
public class AthleteDto {
    int id;
    String firstName;
    String secondName;
    Gender gender;
    LocalDate birthdate;
    Integer clubId;
}
