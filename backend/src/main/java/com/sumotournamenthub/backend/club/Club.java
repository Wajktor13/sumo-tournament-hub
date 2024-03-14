package com.sumotournamenthub.backend.club;

import com.sumotournamenthub.backend.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private Country country;
//    TODO: club coach

}
