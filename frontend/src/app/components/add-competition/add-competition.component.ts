import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Season } from '../../models/season';
import { SeasonService } from '../../services/season/season.service';
import { CompetitionRank } from '../../enums/competition-rank';
import { AgeCategory } from '../../models/age-category';
import { AgeCategoryService } from '../../services/age-category/age-category.service';
import { WeightCategory } from '../../models/weight-category';

@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrls: ['./add-competition.component.css'],
})
export class AddCompetitionComponent implements OnInit {
  todayDate?: string;
  public competitionRanks = Object.values(CompetitionRank);
  public ageCategories: AgeCategory[] = [];
  public seasons: Season[] = [];
  public weightCategoriesMap: { [key: number]: WeightCategory[] } = {};
  addCompetitionForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private seasonService: SeasonService,
    private ageCategoryService: AgeCategoryService
  ) {
    this.addCompetitionForm = this.fb.group({
      competitionName: ['', [Validators.required, Validators.pattern(/^[\p{L}\s\-.']+$/u)]],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      competitionRank: ['', Validators.required],
      season: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.todayDate = new Date().toISOString().slice(0, 10);
    this.fetchSeasons();
  }

  fetchSeasons(): void {
    this.seasonService.getAll().subscribe(
      (seasons: Season[]) => {
        this.seasons = seasons;
      },
      (error) => {
        console.error('Error fetching seasons:', error);
      }
    );
  }

  fetchCategories(seasonId: number): void {
    this.seasonService.getCategories(seasonId).subscribe(
      (categories: AgeCategory[]) => {
        this.ageCategories = categories;
        this.ageCategories.forEach(ageCategory => {
          this.fetchWeightCategories(ageCategory.id);
        });
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

  fetchWeightCategories(ageCategoryId: number): void {
    this.ageCategoryService.getAllWeightCategories(ageCategoryId).subscribe(
      (weightCategories: WeightCategory[]) => {
        this.weightCategoriesMap[ageCategoryId] = weightCategories;
      },
      (error) => {
        console.error('Error fetching weight categories:', error);
      }
    );
  }

  getWeightCategories(ageCategoryId: number): WeightCategory[] {
    return this.weightCategoriesMap[ageCategoryId] || [];
  }

  onSeasonChange(event: any): void {
    const seasonId = event.target.value;
    this.ageCategories = [];
    this.weightCategoriesMap = {};
    this.fetchCategories(seasonId);
  }

  submitForm() {
    if (this.addCompetitionForm.valid) {
      console.log(this.addCompetitionForm.value);
    } else {
      console.error("Invalid input.");
    }
  }
}
