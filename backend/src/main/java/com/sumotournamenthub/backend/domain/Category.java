package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    @Column(name = "age_lower_bound")
    private Integer ageLowerBound;

    @NonNull
    @Column(name = "age_upper_bound")
    private Integer ageUpperBound;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    @NonNull
    @JoinTable(
        name = "category_weight_range", // Name of the join table
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "weight_range_id")
    )
    private Set<WeightRange> weightRanges;

    @ManyToMany(mappedBy = "categories")
    private Set<Season> seasons = new HashSet<>();

}
