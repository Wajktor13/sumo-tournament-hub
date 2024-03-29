package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
    Optional<Athlete> findByClubId(int clubId);
}
