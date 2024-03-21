import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddAthleteComponent } from './add-athlete.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [AddAthleteComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  exports: [AddAthleteComponent],
})
export class AddAthleteModule {}
