import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AgeCategory } from '../../models/age-category';
import { AgeCategoryName } from '../../enums/age-category-name';
import { Gender } from '../../enums/gender';

@Injectable({
  providedIn: 'root',
})
export class AgeCategoryService {
  constructor() {}

  public getAll(): Observable<AgeCategory[]> {
    return of([
      {
        id: 0,
        ageCategoryName: AgeCategoryName.CADET,
        ageLowerBound: 10,
        ageUpperBound: 15,
        gender: Gender.F,
        weightCategories: [
          {
            id: 0,
            weightUpperLimit: 40,
          },
          {
            id: 1,
            weightUpperLimit: 80,
          },
          {
            id: 2,
            weightUpperLimit: 100,
          },
        ],
        openWeightAvailable: true,
      },
      {
        id: 1,
        ageCategoryName: AgeCategoryName.YOUTH,
        ageLowerBound: 20,
        ageUpperBound: 25,
        gender: Gender.M,
        weightCategories: [
          {
            id: 3,
            weightUpperLimit: 40,
          },
          {
            id: 4,
            weightUpperLimit: 120,
          },
          {
            id: 5,
            weightUpperLimit: 200,
          },
        ],
        openWeightAvailable: false,
      },
      {
        id: 0,
        ageCategoryName: AgeCategoryName.SENIOR,
        ageLowerBound: 30,
        ageUpperBound: 90,
        gender: Gender.M,
        weightCategories: [
          {
            id: 6,
            weightUpperLimit: 60,
          },
          {
            id: 7,
            weightUpperLimit: 90,
          },
          {
            id: 8,
            weightUpperLimit: 150,
          },
        ],
        openWeightAvailable: true,
      },
    ]);
  }

  public getOne(id: number): Observable<AgeCategory | undefined> {
    return of({
      id: id,
      ageCategoryName: AgeCategoryName.CADET,
      ageLowerBound: 10,
      ageUpperBound: 15,
      gender: Gender.F,
      weightCategories: [
        {
          id: 0,
          weightUpperLimit: 40,
        },
        {
          id: 1,
          weightUpperLimit: 80,
        },
        {
          id: 2,
          weightUpperLimit: 100,
        },
      ],
      openWeightAvailable: true,
    });
  }
}
