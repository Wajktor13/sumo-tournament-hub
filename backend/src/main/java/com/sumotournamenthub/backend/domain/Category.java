package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

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



}

