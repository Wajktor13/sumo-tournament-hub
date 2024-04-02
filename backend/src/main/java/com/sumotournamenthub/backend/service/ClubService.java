package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.repository.ClubRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository repository;

    public List<Club> getAllClubs() {
        return repository.findAll();
    }

    public Club getClubById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> ExceptionUtils.entityNotFound("Club", id));
    }

    public Set<Athlete> getAllByClubId(int clubId) {
        return repository.findAllAthletesById(clubId).orElse(Collections.emptySet());
    }
}