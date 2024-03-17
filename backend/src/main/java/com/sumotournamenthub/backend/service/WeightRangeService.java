package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.Category;
import com.sumotournamenthub.backend.domain.WeightRange;
import com.sumotournamenthub.backend.repository.WeightRangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeightRangeService {

    private WeightRangeRepository weightRangeRepository;

    public WeightRange addCategoryAndSaveWeightRange(Category category, Integer lowerWeightBound, Integer upperWeightBound) {
        WeightRange weightRange = createOrRetrieveWeightRange(lowerWeightBound, upperWeightBound);
        weightRange.getCategories().add(category);
        return weightRangeRepository.save(weightRange);
    }

    private WeightRange createOrRetrieveWeightRange(Integer lowerWeightBound, Integer upperWeightBound) {
        return weightRangeRepository.findByLowerBoundAndUpperBound(lowerWeightBound, upperWeightBound)
                .orElse(new WeightRange(lowerWeightBound, upperWeightBound));
    }

}
