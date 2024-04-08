package com.sumotournamenthub.backend.domain;

import jakarta.persistence.*;
import lombok.*;

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

}
