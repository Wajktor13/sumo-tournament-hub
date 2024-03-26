import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { WeightCategory } from '../../models/weight-category';

@Injectable({
  providedIn: 'root',
})
export class WeightCategoryService {
  private apiResource = 'weightcategories';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<WeightCategory[]> {
    return this.httpClient.get<WeightCategory[]>(
      `${environment.apiUrl}/${this.apiResource}`,
    );
  }

  public getOne(id: number): Observable<WeightCategory | undefined> {
    return this.httpClient.get<WeightCategory>(
      `${environment.apiUrl}/${this.apiResource}/${id}`,
    );
  }

  public add(
    ageCategory: WeightCategory,
  ): Observable<WeightCategory | undefined> {
    return this.httpClient.post<WeightCategory>(
      `${environment.apiUrl}/${this.apiResource}`,
      ageCategory,
    );
  }

  public getAllByAgeCategoryIdAndCompetitionId(
    ageCategoryId: number,
    competitionId: number,
  ): Observable<WeightCategory[] | undefined> {
    // return this.httpClient.post<WeightCategory>(`${environment.apiUrl}/${this.apiResource}`);

    console.log(ageCategoryId, competitionId); // for lint

    return of([
      {
        id: 1,
        weightUpperLimit: 60,
        openWeight: false,
        competitionId: 1,
        ageCategoryId: 1,
      },
      {
        id: 2,
        weightUpperLimit: 100,
        openWeight: false,
        competitionId: 1,
        ageCategoryId: 1,
      },
      {
        id: 3,
        weightUpperLimit: 150,
        openWeight: false,
        competitionId: 1,
        ageCategoryId: 1,
      },
      {
        id: 3,
        weightUpperLimit: -1,
        openWeight: true,
        competitionId: 1,
        ageCategoryId: 1,
      },
    ]);
  }
}
