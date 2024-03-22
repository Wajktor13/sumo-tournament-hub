package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.WeightCategory;
import com.sumotournamenthub.backend.domain.WeightUpperLimit;
import com.sumotournamenthub.backend.repository.WeightUpperLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeightLimitService {

    private WeightUpperLimitRepository weightLimitRepository;

    public WeightUpperLimit addCategoryAndSaveWeightLimit(WeightCategory category, Integer upperWeightBound) {
        var weightLimit = createOrRetrieveWeightLimit(upperWeightBound);
        weightLimit.getWeightCategories().add(category);
        return weightLimitRepository.save(weightLimit);
    }

    private WeightUpperLimit createOrRetrieveWeightLimit(Integer upperWeightBound) {
        return weightLimitRepository.findByUpperBound(upperWeightBound)
                .orElse(new WeightUpperLimit(upperWeightBound));
    }

}
