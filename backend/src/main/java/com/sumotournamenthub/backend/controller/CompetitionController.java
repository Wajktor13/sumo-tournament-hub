package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.dto.AgeCategoryDto;
import com.sumotournamenthub.backend.service.CompetitionService;
import com.sumotournamenthub.backend.dto.CompetitionDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public List<CompetitionDto> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> getCompetitionById(@PathVariable Integer id) {
        return ResponseEntity.ok(competitionService.getCompetition(id));
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody CompetitionDto dto) {
        var createdCompetition = competitionService.createCompetition(dto);
        return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
    }

    @PutMapping("{id}/file")
    public ResponseEntity<?> addFileToCompetition(@PathVariable Integer id, @RequestParam MultipartFile file) {
        if (competitionService.addFileToCompetition(id, file)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}/file")
    public ResponseEntity<Resource> getFile(@PathVariable Integer id) {
        byte[] file = competitionService.getFileByCompetitionId(id);

        if (file != null) {
            return ResponseEntity.ok(new ByteArrayResource(file));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/ageCategories")
    public ResponseEntity<List<AgeCategoryDto>> getAllAgeCategoriesByCompetitionId(@PathVariable Integer id) {
        List<AgeCategoryDto> ageCategories = competitionService.getAllAgeCategories(id);
        return ResponseEntity.ok(ageCategories);
    }
}