package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
}
