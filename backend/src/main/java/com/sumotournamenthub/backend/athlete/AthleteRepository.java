package com.sumotournamenthub.backend.athlete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
}
