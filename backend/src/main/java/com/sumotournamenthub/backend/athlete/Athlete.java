package com.sumotournamenthub.backend.athlete;

import com.sumotournamenthub.backend.Gender;

import com.sumotournamenthub.backend.club.Club;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name="Athlete")
public class Athlete {

    @Id
    @GeneratedValue
    private int id;
    private String first_name;
    private String second_name;
    @ManyToOne
    private Club club;
    private Gender gender;
    private LocalDate birth_date;

}
