package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Integer> {

}

