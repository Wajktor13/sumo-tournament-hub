package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.repository.ClubRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public List<AthleteDto> getAllByClubId(int clubId) {
        return getClubById(clubId).getAthletes().stream()
                .map(AthleteService::convertToDto)
                .toList();
    }
}