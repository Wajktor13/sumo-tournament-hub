import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';
import { AddCompetitionComponent } from './components/add-competition/add-competition.component';
import { AddSeasonComponent } from './components/add-season/add-season.component';
import { AddAthleteComponent } from './components/add-athlete/add-athlete.component';
import { CompetitionRegistrationComponent } from './components/competition-registration/competition-registration.component';
import { CompetitionsViewComponent } from './components/competitions-view/competitions-view.component';

export const routes: Routes = [
  { path: 'login', pathMatch: 'full', component: LoginComponent },
  { path: 'add-season', pathMatch: 'full', component: AddSeasonComponent },
  { path: 'add-athlete', pathMatch: 'full', component: AddAthleteComponent },
  { path: 'add-competition', pathMatch: 'full', component: AddCompetitionComponent },
  { path: 'competition-registration/:competitionId', pathMatch: 'full', component: CompetitionRegistrationComponent },
  { path: 'competitions-view', pathMatch: 'full', component: CompetitionsViewComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent },
];
