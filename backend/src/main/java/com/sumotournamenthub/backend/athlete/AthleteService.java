package com.sumotournamenthub.backend.athlete;

import java.util.List;

public class AthleteService {

    private AthleteRepository repository;

    public AthleteService(AthleteRepository repository) {
        this.repository = repository;
    }

    public List<Athlete> getAthletes() {
        return repository.findAll();
    }

    public Athlete getAthleteById(int id) {
        return repository.getById(id);
    }

    public void addAthlete(Athlete athlete) {
        repository.save(athlete);
    }

    public void deleteAthlete(Athlete athlete) {
        repository.delete(athlete);
    }
}
