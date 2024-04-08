import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Season } from '../../models/season';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SeasonService {
  private apiResource = 'seasons';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Season[]> {
    return this.httpClient.get<Season[]>(
      `${environment.apiUrl}/${this.apiResource}`,
    );
  }

  public getOne(id: number): Observable<Season | undefined> {
    return this.httpClient.get<Season>(
      `${environment.apiUrl}/${this.apiResource}/${id}`,
    );
  }

  public getUpcoming(): Observable<Season[]> {
    return this.httpClient.get<Season[]>(
      `${environment.apiUrl}/${this.apiResource}/upcoming`,
    );
  }

  public add(athlete: Season): Observable<Season | undefined> {
    return this.httpClient.post<Season>(
      `${environment.apiUrl}/${this.apiResource}`,
      athlete,
    );
  }

  public addAgeCategoryToSeason(seasonId: number, ageCategoryId: number): Observable<any> {
    return this.httpClient.put<number[]>(`${environment.apiUrl}/${this.apiResource}/${seasonId}/addAgeCategory/${ageCategoryId}`, Array.of(seasonId, ageCategoryId))
  }
}
