import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationModule } from './components/navigation/navigation.module';
import { AddSeasonModule } from './components/add-season/add-season.module';
import { AddAthleteModule } from './components/add-athlete/add-athlete.module';
import { LoginModule } from './components/login/login.module';
import { HttpClientModule } from '@angular/common/http';
import { ClubService } from './services/club/club.service';
import { AthleteService } from './services/athlete/athlete.service';
import { SeasonService } from './services/season/season.service';

@NgModule({
  declarations: [],
  imports: [CommonModule, HttpClientModule, NavigationModule, AddSeasonModule, AddAthleteModule, LoginModule],
  exports: [NavigationModule, HttpClientModule],
  providers: [ClubService, AthleteService, SeasonService]
})
export class AppModule {}
