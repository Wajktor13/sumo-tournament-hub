package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository repository;
    private final ClubService clubService;

    public AthleteDto createAthlete(AthleteDto dto) {
        Club club = clubService.getClubById(dto.getClubId());

        var athlete = new Athlete(
                dto.getFirstName(),
                dto.getSecondName(),
                club,
                dto.getGender(),
                dto.getBirthdate());
        return convertToDto(repository.save(athlete));
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