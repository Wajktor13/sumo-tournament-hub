package com.sumotournamenthub.backend.domain;

import com.sumotournamenthub.backend.constants.Role;
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
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String password; // Hashed password will be stored

    @OneToMany(mappedBy = "coach")
    private List<Club> clubs;

    @NonNull
    private Role role;

}
