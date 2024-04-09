package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.CoachRank;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String username;

    @NonNull
    private String password; // Hashed password will be stored

    @NonNull
    @OneToMany(mappedBy = "coach")
    private List<Club> clubs;

    @NonNull
    private CoachRank rank;

}
