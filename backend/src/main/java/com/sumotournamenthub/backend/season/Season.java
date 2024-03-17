package com.sumotournamenthub.backend.season;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Season
{
    @Id
    @GeneratedValue
    private int id;
}
