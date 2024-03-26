package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Integer> {
    List<Season> findByStartDateGreaterThanEqual(LocalDate localDate);
}

