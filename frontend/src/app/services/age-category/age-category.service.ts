import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AgeCategory } from '../../models/age-category';
import { environment } from '../../../environments/environment';
import { AgeCategoryName } from '../../enums/age-category-name';
import { Gender } from '../../enums/gender';

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

  public getByAthleteId(
    athleteId: number,
  ): Observable<AgeCategory | undefined> {
    console.log(athleteId); // for lint

    return of({
      id: 1,
      ageCategoryName: AgeCategoryName.CADET,
      ageLowerBound: 10,
      ageUpperBound: 15,
      gender: Gender.M,
      openWeightAvailable: true,
    });
  }
}
