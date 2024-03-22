package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.constants.Gender;
import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.repository.AthleteRepository;
import com.sumotournamenthub.backend.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sumotournamenthub.backend.utils.ExceptionUtils.notExist;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository repository;
    private final ClubRepository clubRepository;

    public Athlete createAthlete(AthleteDto dto) {
        var club = clubRepository
                .findById(dto.getClubId())
                .orElseThrow(() -> notExist(format("Club with %d id does not exist", dto.getClubId())));
        var athlete = new Athlete(
                dto.getFirstName(),
                dto.getSecondName(),
                club,
                Gender.fromString(dto.getGender()),
                dto.getBirthdate());
        return repository.save(athlete);
    }

}
