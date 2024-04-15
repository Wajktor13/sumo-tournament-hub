package com.sumotournamenthub.backend.config;

import com.sumotournamenthub.backend.constants.AgeCategoryName;
import com.sumotournamenthub.backend.constants.Country;
import com.sumotournamenthub.backend.constants.Gender;
import com.sumotournamenthub.backend.domain.*;
import com.sumotournamenthub.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Configuration
public class SumoTournamentHubBackendApplicationConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ClubRepository clubRepository, SeasonRepository seasonRepository,
                                        CompetitionRepository competitionRepository,
                                        AthleteRepository athleteRepository,
                                        AgeCategoryRepository ageCategoryRepository,
                                        WeightCategoryRepository weightCategoryRepository) {
        return args -> {
            // Club
            Club club1 = new Club("Czerwony Smok", Country.POLAND);
            clubRepository.save(club1);

            Club club2 = new Club("Spartakus", Country.GERMANY);
            clubRepository.save(club2);

            // Season
            Season season1 = new Season("Spring 2024", LocalDate.of(2024, 3, 20),
                    LocalDate.of(2024, 6, 20));
            seasonRepository.save(season1);

            Season season2 = new Season("Summer 2024", LocalDate.of(2024, 6, 21),
                    LocalDate.of(2024, 9, 10));
            seasonRepository.save(season2);

            // Competition
            Competition competition1 = new Competition("Clash of Titans", season1,
                    LocalDate.of(2024, 4, 20), LocalDate.of(2024, 4, 24),
                    "National");
            competitionRepository.save(competition1);

            // Athlete
            Athlete athlete1 = new Athlete("John", "Cena", club1, Gender.M,
                    LocalDate.of(1989, 4, 24));
            athleteRepository.save(athlete1);
            club1.getAthletes().add(athlete1);

            Athlete athlete2 = new Athlete("Hermiona", "Doe", club1, Gender.F,
                    LocalDate.of(2002, 7, 13));
            athleteRepository.save(athlete2);
            club1.getAthletes().add(athlete2);

            clubRepository.save(club1);

            Athlete athlete3 = new Athlete("Jan", "Kowalski", club2, Gender.M,
                    LocalDate.of(1967, 1, 23));
            athleteRepository.save(athlete3);
            club2.getAthletes().add(athlete3);

            clubRepository.save(club2);

            // AgeCategories
            AgeCategory ageCategory1 = new AgeCategory(AgeCategoryName.SENIOR, 5, 99,
                    Gender.M);
            ageCategory1.getSeasons().add(season1);
            ageCategory1.getSeasons().add(season2);
            ageCategoryRepository.save(ageCategory1);

            season1.getCategories().add(ageCategory1);
            season2.getCategories().add(ageCategory1);
            seasonRepository.save(season1);
            seasonRepository.save(season2);

            // WeightCategories
            WeightCategory weightCategory1 = new WeightCategory(ageCategory1, competition1, 60);
            WeightCategory weightCategory2 = new WeightCategory(ageCategory1, competition1, 80);
            WeightCategory weightCategory3 = new WeightCategory(ageCategory1, competition1, 120);
            WeightCategory weightCategory4 = new WeightCategory(ageCategory1, competition1, -1);
            weightCategory4.setOpenWeight(true);

            Set<WeightCategory> weightCategories = Set.of(weightCategory1, weightCategory2, weightCategory3, weightCategory4);
            weightCategoryRepository.saveAll(weightCategories);

            ageCategory1.setWeightCategories(weightCategories);
            ageCategoryRepository.save(ageCategory1);
        };
    }
}
