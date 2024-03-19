import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';
import { AddSeasonComponent } from './components/add-season/add-season.component';
import { AddAthleteComponent } from './components/add-athlete/add-athlete.component';

export const routes: Routes = [
  { path: 'login', pathMatch: 'full', component: LoginComponent },
  { path: 'add-season', pathMatch: 'full', component: AddSeasonComponent },
  { path: 'add-athlete', pathMatch: 'full', component: AddAthleteComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent },
];
