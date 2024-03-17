package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.constants.Gender;
import com.sumotournamenthub.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByNameAndAgeLowerBoundAndAgeUpperBoundAndGender(String name, Integer ageLowerBound, Integer ageUpperBound, Gender gender);

}
