package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "weight_upper_limit")
public class WeightUpperLimit {

    @Id
    @GeneratedValue
    private Integer id;

    // Open Weight Category is represented as 0
    @NonNull
    @Column(name="upper_bound")
    private Integer upperBound;

    @ManyToMany(mappedBy = "weightUpperLimits")
    private Set<WeightCategory> weightCategories = new HashSet<>();

}