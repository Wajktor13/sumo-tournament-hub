package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.Athlete;
import com.sumotournamenthub.backend.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Set;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    Optional<Set<Athlete>> findAllAthletesById(Integer id);
}
