package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.dto.ClubDto;
import com.sumotournamenthub.backend.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDto> getAll() {
        List<ClubDto> clubDtoList = new ArrayList<>();
        for (Club club : clubService.getAllClubs()) {
            clubDtoList.add(convertToDto(club));
        }
        return clubDtoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDto> getById(@PathVariable Integer id) {
        ClubDto clubdto = convertToDto(clubService.getClubById(id));
        return new ResponseEntity<>(clubdto, HttpStatus.OK);
    }

    private ClubDto convertToDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .country(club.getCountry())
                .name(club.getName())
                .build();
    }
}
