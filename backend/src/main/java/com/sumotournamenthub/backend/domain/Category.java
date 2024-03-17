package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "category")
public class Category {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age_lower_bound", nullable = false)
    private Integer ageLowerBound;

    @Column(name = "age_upper_bound", nullable = false)
    private Integer ageUpperBound;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @ManyToMany
    @JoinTable(
        name = "category_weight_range", // Name of the join table
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "weight_range_id")
    )
    private Set<WeightRange> weightRanges;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "categories")
    private Set<Season> seasons;

}
