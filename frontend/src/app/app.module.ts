import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationModule } from './components/navigation/navigation.module';
import { AddSeasonModule } from './components/add-season/add-season.module';

@NgModule({
  declarations: [],
  imports: [CommonModule, NavigationModule, AddSeasonModule],
  exports: [NavigationModule],
})
export class AppModule {}
