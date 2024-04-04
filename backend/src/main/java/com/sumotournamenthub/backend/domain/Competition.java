package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.Country;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "competition")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Competition
{
    @Id
    @GeneratedValue
    private int id;

    private @NonNull String name;

    @ManyToOne
    private @NonNull Season season;

    private @NonNull LocalDate startTime;

    private @NonNull LocalDate endTime;

    @OneToMany
    private @NonNull Set<Athlete> signedAthletes;

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Country, Integer> countryLimits;

    @OneToMany
    private @NonNull Set<WeightCategory> availableCategories;

    private @NonNull String rank;
}
