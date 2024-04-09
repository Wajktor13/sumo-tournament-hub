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

    @NonNull
    @ManyToOne
    @JoinColumn(name = "age_category_id")
    private AgeCategory ageCategory;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "weightCategory")
    private Set<Registration> registrations;

    @NonNull
    @Column(name = "weight_upper_limit")
    private Integer weightUpperLimit;

    @Column(name = "open_weight")
    private boolean openWeight;

}
