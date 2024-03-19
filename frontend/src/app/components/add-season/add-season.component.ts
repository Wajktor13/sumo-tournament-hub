import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-season',
  standalone: false,
  templateUrl: './add-season.component.html',
  styleUrl: './add-season.component.css'
})
export class AddSeasonComponent {
  addAgeCategoryForm = new FormGroup({
    ageCategoryName: new FormControl('', []),
    ageLowerBound: new FormControl('', []),
    ageUpperBound: new FormControl('', []),
    gender: new FormControl('', [])
  })

  addedCategories: Array<Category> = [];

  addCategory(): void {
    const ageCategory = {
      name: this.addAgeCategoryForm.get('ageCategoryName')!.value,
      lowerBound: this.addAgeCategoryForm.get('ageLowerBound')!.value,
      upperBound: this.addAgeCategoryForm.get('ageUpperBound')!.value,
      gender: this.addAgeCategoryForm.get('gender')!.value
    } as unknown as Category;

    console.log('%d', ageCategory.lowerBound);

    this.addedCategories.push(ageCategory);
  }
}

export interface Category {
  name: string;
  lowerBound: number;
  upperBound: number;
  gender: string;
}
