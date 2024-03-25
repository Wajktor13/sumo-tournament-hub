import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddCompetitionComponent } from './add-competition.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AddCompetitionComponent],
  imports: [CommonModule, FormsModule],
  exports: [AddCompetitionComponent],
})
export class AddCompetitionModule {}
