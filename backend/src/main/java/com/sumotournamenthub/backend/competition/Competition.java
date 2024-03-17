package com.sumotournamenthub.backend.competition;

import com.sumotournamenthub.backend.Athlete;
import com.sumotournamenthub.backend.Category;
import com.sumotournamenthub.backend.Season;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Competition")
public class Competition
{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private Season season;
    private LocalDate startTime;
    private LocalDate endTime;

    @ManyToMany
    private Set<Athlete> signedAthletes;
    private int maxAthletesPerCountry;
    @ManyToMany
    private Set<Category> availableCategories;
}
