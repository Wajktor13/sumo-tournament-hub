package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    boolean existsByAthleteIdAndWeightCategoryId(Integer athleteId, Integer weightCategoryId);

}
