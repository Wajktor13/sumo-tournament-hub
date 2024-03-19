package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.repository.AthleteRepository;

import java.util.List;
import java.util.Optional;

public class AthleteService {

    private AthleteRepository repository;

    public AthleteService(AthleteRepository repository) {
        this.repository = repository;
    }

    public List<Athlete> getAthletes() {
        return repository.findAll();
    }

    public Optional<Athlete> getAthleteById(int id) {
        return repository.findById(id);
    }

    public void addAthlete(Athlete athlete) {
        repository.save(athlete);
    }

    public void deleteAthlete(Athlete athlete) {
        repository.delete(athlete);
    }
}
