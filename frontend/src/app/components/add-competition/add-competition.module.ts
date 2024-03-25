import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddCompetitionComponent } from './add-competition.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [AddCompetitionComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  exports: [AddCompetitionComponent],
})
export class AddCompetitionModule {}
