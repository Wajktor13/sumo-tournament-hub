package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.WeightUpperLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeightUpperLimitRepository extends JpaRepository<WeightUpperLimit, Integer> {

    Optional<WeightUpperLimit> findByUpperBound(Integer upperBound);

}
