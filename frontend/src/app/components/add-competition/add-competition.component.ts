import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Season } from '../../models/season';
import { SeasonService } from '../../services/season/season.service';
import { CompetitionRank } from '../../enums/competition-rank';
import { AgeCategory } from '../../models/age-category'; // Update import
import { AgeCategoryName } from '../../enums/age-category-name';

@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrls: ['./add-competition.component.css'],
})
export class AddCompetitionComponent implements OnInit {
  todayDate?: string;
  public competitionRanks = Object.values(CompetitionRank);
  public ageCategories: AgeCategory[] = []; // Update type
  public seasons: Season[] = [];
  addCompetitionForm: FormGroup;

  constructor(private fb: FormBuilder, private seasonService: SeasonService) {
    this.addCompetitionForm = this.fb.group({
      competitionName: ['', [Validators.required, Validators.pattern(/^[\p{L}\s\-.']+$/u)]],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      competitionRank: ['', Validators.required],
      season: ['', Validators.required],
      ageCategories: this.fb.array([]) // Initialize an empty FormArray
    });
  }

  ngOnInit() {
    this.todayDate = new Date().toISOString().slice(0, 10);
    this.fetchSeasons();
  }

  private fetchSeasons(): void {
    this.seasonService.getAll().subscribe(
      (seasons: Season[]) => {
        this.seasons = seasons;
        const currentSeason = this.seasons.find(season => season.id === 1); // Assuming the season id is 1
        if (currentSeason) {
          this.seasonService.getCategories(currentSeason.id).subscribe(
            (categories: AgeCategory[]) => { // Update type
              this.ageCategories = categories;
              this.initCategoryCheckboxes();
            },
            (error) => {
              console.error('Error fetching categories:', error);
            }
          );
        }
      },
      (error) => {
        console.error('Error fetching seasons:', error);
      }
    );
  }

  // Initialize checkboxes for each category
  private initCategoryCheckboxes(): void {
    const categoryArray = this.addCompetitionForm.get('ageCategories') as FormArray;
    this.ageCategories.forEach(() => {
      categoryArray.push(this.fb.control(true)); // Initialize each checkbox as checked
    });
  }

  onCategoryChange(index: number, isChecked: boolean): void {
    const categories = this.addCompetitionForm.get('ageCategories') as FormArray;
    categories.at(index).setValue(isChecked);
  }

  submitForm() {
    if (this.addCompetitionForm.valid) {
      // Extract selected categories
      const selectedCategories = this.addCompetitionForm.value.ageCategories
        .map((checked: boolean, i: number) => checked ? this.ageCategories[i].ageCategoryName : null) // Update to access ageCategoryName
        .filter((v: string | null) => v !== null);
      console.log(selectedCategories);
      console.log(this.addCompetitionForm.value);
    } else {
      console.error("Invalid input.");
    }
  }
}
