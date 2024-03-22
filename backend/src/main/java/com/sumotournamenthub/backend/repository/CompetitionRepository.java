package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

}
