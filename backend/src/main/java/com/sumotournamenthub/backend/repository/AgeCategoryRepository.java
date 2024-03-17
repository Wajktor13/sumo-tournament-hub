package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.constants.AgeCategoryName;
import com.sumotournamenthub.backend.constants.Gender;
import com.sumotournamenthub.backend.domain.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Integer> {

    Optional<AgeCategory> findByNameAndAgeLowerBoundAndAgeUpperBoundAndGender(AgeCategoryName name, Integer ageLowerBound, Integer ageUpperBound, Gender gender);

}
