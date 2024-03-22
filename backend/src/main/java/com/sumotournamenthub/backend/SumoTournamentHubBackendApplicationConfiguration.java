package com.sumotournamenthub.backend;

import com.sumotournamenthub.backend.constants.Country;
import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.repository.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SumoTournamentHubBackendApplicationConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ClubRepository clubRepository) {
        return args -> {
            Club club1 = new Club("Czerwony Smok", Country.POLAND);
            clubRepository.save(club1);

            Club club2 = new Club("Spartakus", Country.GERMANY);
            clubRepository.save(club2);
        };
    }
}
