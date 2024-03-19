import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddSeasonComponent } from './add-season.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [AddSeasonComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  exports: [AddSeasonComponent],
})
export class AddSeasonModule {}
