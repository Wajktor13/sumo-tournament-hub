package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "weight_category_id")
    private WeightCategory weightCategory;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

}
