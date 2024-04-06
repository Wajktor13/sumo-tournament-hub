package com.sumotournamenthub.backend.config;

import com.sumotournamenthub.backend.constants.Country;
import com.sumotournamenthub.backend.domain.Club;
import com.sumotournamenthub.backend.domain.Season;
import com.sumotournamenthub.backend.repository.ClubRepository;
import com.sumotournamenthub.backend.repository.SeasonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class SumoTournamentHubBackendApplicationConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ClubRepository clubRepository, SeasonRepository seasonRepository) {
        return args -> {
            Club club1 = new Club("Czerwony Smok", Country.POLAND);
            clubRepository.save(club1);

            Club club2 = new Club("Spartakus", Country.GERMANY);
            clubRepository.save(club2);

            Season season1 = new Season("Spring 2024", LocalDate.of(2024, 3, 20),
                    LocalDate.of(2024, 6, 20));
            seasonRepository.save(season1);

            Season season2 = new Season("Summer 2024", LocalDate.of(2024, 6, 21),
                    LocalDate.of(2024, 9, 10));
            seasonRepository.save(season2);
        };
    }
}
