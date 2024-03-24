import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompetitionRegistrationComponent } from './competition-registration.component';



@NgModule({
  declarations: [CompetitionRegistrationComponent],
  imports: [
    CommonModule
  ],
  exports: [CompetitionRegistrationComponent]
})
export class CompetitionRegistrationModule { }
