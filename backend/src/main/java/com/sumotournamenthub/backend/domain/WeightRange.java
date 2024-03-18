package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "weight_range")
public class WeightRange {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @Column(name="lower_bound")
    private Integer lowerBound;

    @NonNull
    @Column(name="upper_bound")
    private Integer upperBound;

    @ManyToMany(mappedBy = "weightRanges")
    private Set<WeightCategory> weightCategories = new HashSet<>();

}