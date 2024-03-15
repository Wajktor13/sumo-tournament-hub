import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationModule } from './components/navigation/navigation.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    NavigationModule
  ], exports: [
    NavigationModule
  ]
})
export class AppModule { }
