package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.Gender;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name="Athlete")
@RequiredArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Athlete {

    @Id
    @GeneratedValue
    private int id;

    private @NonNull String firstName;

    private @NonNull String secondName;

    @ManyToOne
    private @NonNull Club club;

    private @NonNull Gender gender;

    private @NonNull LocalDate birthDate;

}
