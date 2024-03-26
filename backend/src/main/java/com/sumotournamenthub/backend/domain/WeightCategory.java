package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weight_category")
public class WeightCategory {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "age_category_id")
    private AgeCategory ageCategory;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToMany
    @NonNull
    @JoinTable(
            name = "category_weight_limit", // Name of the join table
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "weight_limit_id")
    )
    private Set<WeightUpperLimit> weightUpperLimits;

}
