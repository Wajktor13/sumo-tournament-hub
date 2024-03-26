package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.AgeCategoryName;
import com.sumotournamenthub.backend.constants.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "age_category")
public class AgeCategory {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AgeCategoryName name;

    @NonNull
    @Column(name = "age_lower_bound")
    private Integer ageLowerBound;

    @NonNull
    @Column(name = "age_upper_bound")
    private Integer ageUpperBound;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "ageCategory")
    private Set<WeightCategory> weightCategories;

    @ManyToMany(mappedBy = "categories")
    private Set<Season> seasons = new HashSet<>();

}
