package com.sumotournamenthub.backend.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category
{
    @Id
    @GeneratedValue
    private int id;
}
