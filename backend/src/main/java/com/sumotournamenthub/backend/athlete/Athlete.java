package com.sumotournamenthub.backend.athlete;

import com.sumotournamenthub.backend.Gender;

import com.sumotournamenthub.backend.club.Club;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name="Athlete")
@RequiredArgsConstructor
@Getter
@Setter
public class Athlete {

    @Id
    @GeneratedValue
    private int id;
    private @NonNull String first_name;
    private @NonNull String second_name;
    @ManyToOne
    private @NonNull Club club;
    private @NonNull Gender gender;
    private @NonNull LocalDate birth_date;

}
