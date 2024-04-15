import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AgeCategory } from '../../models/age-category';
import { environment } from '../../../environments/environment';
import { WeightCategory } from '../../models/weight-category';

@Injectable({
  providedIn: 'root',
})
export class AgeCategoryService {
  private apiResource = 'ageCategories';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<AgeCategory[]> {
    return this.httpClient.get<AgeCategory[]>(
      `${environment.apiUrl}/${this.apiResource}`,
    );
  }

  public getOne(id: number): Observable<AgeCategory | undefined> {
    return this.httpClient.get<AgeCategory>(
      `${environment.apiUrl}/${this.apiResource}/${id}`,
    );
  }

  public add(ageCategory: AgeCategory): Observable<AgeCategory | undefined> {
    return this.httpClient.post<AgeCategory>(
      `${environment.apiUrl}/${this.apiResource}`,
      ageCategory,
    );
  }

  public getAllWeightCategories(id: number): Observable<WeightCategory[]> {
    return this.httpClient.get<WeightCategory[]>(
      `${environment.apiUrl}/${this.apiResource}/${id}/weightCategories`,
    );
  }

  public getAgeCategoryByAthletesIdsAndSeasonId(
    athleteIds: number[],
    seasonId: number,
  ): Observable<AgeCategory> {
    let params = new HttpParams();

    const athleteIdsString = athleteIds.join(',');

    params = params.append('athleteIds', athleteIdsString);
    params = params.append('seasonId', seasonId.toString());

    return this.httpClient.get<AgeCategory>(
      `${environment.apiUrl}/${this.apiResource}/byAthletesAndSeason`,
      { params: params },
    );
  }
}
