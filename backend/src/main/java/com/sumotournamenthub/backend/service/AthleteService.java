package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository repository;
    private final ClubService clubService;

    public Athlete createAthlete(AthleteDto dto) {
        Club club = clubService.getClubById(dto.getClubId());

        var athlete = new Athlete(
                dto.getFirstName(),
                dto.getSecondName(),
                club,
                dto.getGender(),
                dto.getBirthdate());
        return repository.save(athlete);
    }

    public Set<Athlete> getAllByClubId(int clubId) {
        return repository.findByClubId(clubId).map(Collections::singleton).orElse(Collections.emptySet());
    }

}