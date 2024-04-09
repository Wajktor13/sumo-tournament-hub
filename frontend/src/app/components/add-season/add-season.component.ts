import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AgeCategory } from '../../models/age-category';
import { OnInit } from '@angular/core';
import { AgeCategoryName } from '../../enums/age-category-name';
import { SeasonService } from '../../services/season/season.service';
import { AgeCategoryService } from '../../services/age-category/age-category.service';
import { Season } from '../../models/season';

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

  constructor(private seasonService: SeasonService, private ageCategoryService: AgeCategoryService) { }

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
    return this.createdAgeCategories.filter((ageCategory) => ageCategory.ageCategoryName === this.addAgeCategoryForm.value.ageCategoryName?.toUpperCase() && 
    ageCategory.gender === this.addAgeCategoryForm.value.gender).length === 0
  }

  deleteCreatedAgeCategory(ageCategory: AgeCategory): void {
    const index = this.createdAgeCategories.indexOf(ageCategory, 0);
    if (index > -1) {
      this.createdAgeCategories.splice(index, 1);
    }
  }

  calculateYearOfBirth(endDate: string, age: string | number) {
    if (endDate && age) {
      const year: number = new Date(endDate).getFullYear();
      return year - Number(age);
    }
    return "???";
  }

  addCategory(): void {
    const data: any = this.addAgeCategoryForm.value;

    const createdAgeCategory: AgeCategory = {
      id: 0,
      ageCategoryName: data.ageCategoryName.toUpperCase(),
      ageLowerBound: data.ageLowerBound,
      ageUpperBound: data.ageUpperBound,
      gender: data.gender,
    }

    this.createdAgeCategories.push(createdAgeCategory);
  }
  
  addSeason(): void {
    const seasonData: any = this.addSeasonForm.value;

    const season: Season = {
      id: 0,
      name: seasonData.name,
      startDate: seasonData.startDate,
      endDate: seasonData.endDate,
    }

    this.seasonService.add(season).subscribe(
      {
        next: vS => {
          console.log(vS);
          this.createdAgeCategories.forEach((ageCategory) => {
            this.ageCategoryService.add(ageCategory).subscribe(
              {
                next: vAC => {
                  console.log(vAC);
                  this.seasonService.addAgeCategoryToSeason(vS!.id, vAC!.id).subscribe(
                    {
                      next: v => console.log(v),
                      error: e => console.log(e)
                    }
                  )
                },
                error: e => console.log(e)
              }
            )
          })
        },
        error: e => console.log(e)
      }
    )
  }
}
