import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationModule } from './components/navigation/navigation.module';
import { AddSeasonModule } from './components/add-season/add-season.module';
import { LoginModule } from './components/login/login.module';

@NgModule({
  declarations: [],
  imports: [CommonModule, NavigationModule, AddSeasonModule, LoginModule],
  exports: [NavigationModule],
})
export class AppModule {}
