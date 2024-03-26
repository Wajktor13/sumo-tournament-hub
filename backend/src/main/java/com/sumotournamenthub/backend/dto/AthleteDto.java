package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.Gender;
import com.sumotournamenthub.backend.domain.Athlete;
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

    public static AthleteDto convertToDto(Athlete athlete) {
        return AthleteDto.builder()
                .id(athlete.getId())
                .firstName(athlete.getFirstName())
                .secondName(athlete.getSecondName())
                .gender(athlete.getGender())
                .birthdate(athlete.getBirthDate())
                .clubId(athlete.getClub().getId())
                .build();
    }
}
