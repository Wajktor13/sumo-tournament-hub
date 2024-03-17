package com.sumotournamenthub.backend.competition;

import com.sumotournamenthub.backend.athlete.Athlete;
import com.sumotournamenthub.backend.category.Category;
import com.sumotournamenthub.backend.season.Season;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "competition")
@RequiredArgsConstructor
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
    private int maxAthletesPerCountry;
    @OneToMany
    private @NonNull Set<Category> availableCategories;
}
