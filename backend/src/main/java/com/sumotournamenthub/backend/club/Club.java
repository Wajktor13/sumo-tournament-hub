package com.sumotournamenthub.backend.club;

import com.sumotournamenthub.backend.constants.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "club")
@RequiredArgsConstructor
@Getter @Setter
public class Club {

    @Id
    @GeneratedValue
    private int id;

    private @NonNull String name;

    private @NonNull Country country;
//    TODO: club coach

}
