package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.repository.ClubRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sumotournamenthub.backend.utils.ExceptionUtils.notExist;
import static java.lang.String.format;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubRepository repository;

    public ClubController(ClubRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Club> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable Integer id) {
        var club = repository.findById(id).
                orElseThrow(() -> notExist(format("Club with id %d does not exist", id)));
        return new ResponseEntity<>(club, HttpStatus.OK);
    }

}
