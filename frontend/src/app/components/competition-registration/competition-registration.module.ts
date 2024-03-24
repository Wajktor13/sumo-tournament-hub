import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompetitionRegistrationComponent } from './competition-registration.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [CompetitionRegistrationComponent],
  imports: [CommonModule, FormsModule],
  exports: [CompetitionRegistrationComponent],
})
export class CompetitionRegistrationModule {}
