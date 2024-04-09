import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompetitionsViewComponent } from './competitions-view.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [CompetitionsViewComponent],
  imports: [CommonModule, FormsModule],
  exports: [CompetitionsViewComponent],
})
export class CompetitionsViewModule {}
