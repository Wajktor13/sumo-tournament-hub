package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(
            name = "season_category", // Name of the join table
            joinColumns = @JoinColumn(name = "season_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

}

