package com.sumotournamenthub.backend.repository;

import com.sumotournamenthub.backend.domain.WeightRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeightRangeRepository extends JpaRepository<WeightRange, Integer> {

    Optional<WeightRange> findByLowerBoundAndUpperBound(Integer lowerBound, Integer upperBound);

}
