package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.dto.AthleteDto;
import com.sumotournamenthub.backend.dto.ClubDto;
import com.sumotournamenthub.backend.repository.ClubRepository;
import com.sumotournamenthub.backend.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository repository;

    public List<ClubDto> getAllClubs() {
        return repository.findAll().stream().map(ClubService::convertToDto).toList();
    }

    public ClubDto getClub(Integer id) {
        return convertToDto(getClubEntity(id));
    }

    public Club getClubEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> ExceptionUtils.entityNotFound("Club", id));
    }

    public List<AthleteDto> getAllAthletes(int clubId) {
        return getClubEntity(clubId).getAthletes().stream()
                .map(AthleteService::convertToDto)
                .toList();
    }

    public static ClubDto convertToDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .country(club.getCountry())
                .name(club.getName())
                .build();
    }
}