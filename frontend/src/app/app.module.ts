import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationModule } from './components/navigation/navigation.module';
import { AddSeasonModule } from './components/add-season/add-season.module';
import { AddAthleteModule } from './components/add-athlete/add-athlete.module';
import { LoginModule } from './components/login/login.module';
import { HttpClientModule } from '@angular/common/http';
import { ClubService } from './services/club/club.service';
import { AthleteService } from './services/athlete/athlete.service';
import { AgeCategoryService } from './services/age-category/age-category.service';
import { WeightCategoryService } from './services/weight-category/weight-category.service';
import { CompetitionService } from './services/competition/competition.service';
import { SeasonService } from './services/season/season.service';
import { CompetitionRegistrationModule } from './components/competition-registration/competition-registration.module';
import { AddCompetitionModule } from './components/add-competition/add-competition.module';
import { CompetitionsViewModule } from './components/competitions-view/competitions-view.module';
import { RegistrationService } from './services/registration/registration.service';
import { HomePageModule } from './components/home-page/home-page.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    NavigationModule,
    AddSeasonModule,
    AddAthleteModule,
    LoginModule,
    AddCompetitionModule,
    CompetitionRegistrationModule,
    CompetitionsViewModule,
    HomePageModule
  ],
  exports: [NavigationModule, HttpClientModule],
  providers: [
    ClubService,
    AthleteService,
    CompetitionService,
    SeasonService,
    AgeCategoryService,
    WeightCategoryService,
    RegistrationService,
  ],
})
export class AppModule {}
