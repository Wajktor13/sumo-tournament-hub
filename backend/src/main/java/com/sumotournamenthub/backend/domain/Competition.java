package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.Country;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competition")
public class Competition {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private String name;

    @NonNull
    @ManyToOne
    private Season season;

    @NonNull
    private LocalDate startTime;

    @NonNull
    private LocalDate endTime;

    @OneToMany(mappedBy = "competition")
    private Set<WeightCategory> availableCategories;

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Country, Integer> countryLimits;

}
