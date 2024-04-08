package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

import static com.sumotournamenthub.backend.utils.ExceptionUtils.entityNotFound;

@Service
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository repository;
    private final ClubService clubService;

    public AthleteDto createAthlete(AthleteDto dto) {
        var club = clubService.getClubEntity(dto.getClubId());

        var athlete = new Athlete(
                dto.getFirstName(),
                dto.getSecondName(),
                club,
                dto.getGender(),
                dto.getBirthdate());
        return convertToDto(repository.save(athlete));
    }

    public Athlete getAthleteEntity(int id) {
        return repository.findById(id).orElseThrow(() -> entityNotFound("Athlete", id));
    }

    public Integer calculateAthleteAge(int id) {
        var athlete = getAthleteEntity(id);
        return Period.between(athlete.getBirthDate(), LocalDate.now()).getYears();
    }

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