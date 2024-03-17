package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "weight_range")
public class WeightRange {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="lower_bound")
    private Integer lowerBound;

    @Column(name="upper_bound")
    private Integer upperBound;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "weightRanges")
    private Set<Category> categories;

}
