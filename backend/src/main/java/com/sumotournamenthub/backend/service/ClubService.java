package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sumotournamenthub.backend.utils.ExceptionUtils.notExist;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository repository;

    public List<Club> getAllClubs() {
        return repository.findAll();
    }

    public Club getClubById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> notExist(format("Club with %d id does not exist", id)));
    }
}
