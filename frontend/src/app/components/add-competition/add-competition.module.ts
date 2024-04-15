import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddCompetitionComponent } from './add-competition.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';

@NgModule({
  declarations: [AddCompetitionComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatCheckboxModule],
  exports: [AddCompetitionComponent],
})
export class AddCompetitionModule {}
