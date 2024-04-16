package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.Country;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "club")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Club {

    @Id
    @GeneratedValue
    private int id;

    private @NonNull String name;

    private @NonNull Country country;

    @OneToMany(mappedBy = "club")
    private Set<Athlete> athletes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User coach;

}
