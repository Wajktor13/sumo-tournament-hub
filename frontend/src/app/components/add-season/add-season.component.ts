import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AgeCategory } from '../../models/age-category';
import { OnInit } from '@angular/core';
import { AgeCategoryName } from '../../enums/age-category-name';

@Component({
  selector: 'app-add-season',
  standalone: false,
  templateUrl: './add-season.component.html',
  styleUrl: './add-season.component.css'
})
export class AddSeasonComponent implements OnInit {
  todayDate?: string;
  ageCategoryNames: Array<string> = Object.values(AgeCategoryName);

  createdAgeCategories:Array<AgeCategory> = [];

  ngOnInit() {
    this.todayDate = new Date().toISOString().slice(0, 10);
  }

  addSeasonForm = new FormGroup({
    name: new FormControl('', [
      Validators.required
    ]),
    startDate: new FormControl('', [
      Validators.required
    ]),
    endDate: new FormControl('', [
      Validators.required
    ])
  })

  addAgeCategoryForm = new FormGroup({
    ageCategoryName: new FormControl('', [
      Validators.required
    ]),
    ageLowerBound: new FormControl('', [
      Validators.required
    ]),
    ageUpperBound: new FormControl('', [
      Validators.required
    ]),
    gender: new FormControl('', [
      Validators.required
    ])
  })

  checkForAlreadyExistingCategory(): boolean {
    return this.createdAgeCategories.filter((ageCategory) => ageCategory.ageCategoryName === this.addAgeCategoryForm.value.ageCategoryName && 
    ageCategory.gender === this.addAgeCategoryForm.value.gender).length === 0
  }

  deleteCreatedAgeCategory(ageCategory: AgeCategory): void {
    const index = this.createdAgeCategories.indexOf(ageCategory, 0);
    if (index > -1) {
      this.createdAgeCategories.splice(index, 1);
    }
  }

  addCategory(): void {
    const data: any = this.addAgeCategoryForm.value

    const createdAgeCategory: AgeCategory = {
      id: 0,
      ageCategoryName: data.ageCategoryName,
      ageLowerBound: data.ageLowerBound,
      ageUpperBound: data.ageUpperBound,
      gender: data.gender,
      openWeightAvailable: false
    }

    this.createdAgeCategories.push(createdAgeCategory);
  }
}
