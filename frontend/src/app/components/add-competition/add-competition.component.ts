import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
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
      categories: this.fb.array([])
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
        this.addCompetitionForm.setControl('categories', this.buildCategoryControls());
        this.ageCategories.forEach(ageCategory => {
          this.fetchWeightCategories(ageCategory.id);
        });
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

  buildCategoryControls(): FormArray {
    return this.fb.array(this.ageCategories.map(category => 
      this.fb.group({
        id: category.id,
        checked: false,
        weightCategories: this.fb.array([])
      })
    ));
  }

  updateWeightCategories(ageCategoryId: number): void {
    const index = this.ageCategories.findIndex(c => c.id === ageCategoryId);
    const categoryControl = this.categories.at(index) as FormGroup;
    categoryControl.setControl('weightCategories', this.fb.array(
      this.getWeightCategories(ageCategoryId).map(wc => 
        this.fb.group({
          checked: false,
          weightCategoryId: wc.id
        })
      )
    ));
  }

  fetchWeightCategories(ageCategoryId: number): void {
    this.ageCategoryService.getAllWeightCategories(ageCategoryId).subscribe(
      (weightCategories: WeightCategory[]) => {
        this.weightCategoriesMap[ageCategoryId] = weightCategories;
        this.updateWeightCategories(ageCategoryId);
      },
      (error) => {
        console.error('Error fetching weight categories:', error);
      }
    );
  }

  getWeightCategoryControls(ageCategoryId: number) {
    const index = this.ageCategories.findIndex(c => c.id === ageCategoryId);
    if (index !== -1) {
      const categoryControl = this.categories.at(index) as FormGroup;
      return (categoryControl.get('weightCategories') as FormArray).controls;
    }
    return [];
  }
  
  getWeightCategories(ageCategoryId: number): WeightCategory[] {
    if (ageCategoryId !== undefined) {
      return this.weightCategoriesMap[ageCategoryId] || [];
    }
    return [];
  }

  onSeasonChange(event: any): void {
    const seasonId = event.target.value;
    this.ageCategories = [];
    this.weightCategoriesMap = {};
    this.fetchCategories(seasonId);
  }

  get categories(): FormArray {
    return this.addCompetitionForm.get('categories') as FormArray;
  }

  submitForm() {
    console.log(this.addCompetitionForm.value);
  }
}
