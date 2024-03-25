import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Competition } from '../../models/competition';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Country } from '../../enums/country';
import { CompetitionRank } from '../../enums/competition-rank';

@Injectable({
  providedIn: 'root',
})
export class CompetitionService {
  private apiResource = 'competitions';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Competition[]> {
    return this.httpClient.get<Competition[]>(
      `${environment.apiUrl}/${this.apiResource}`,
    );
  }

  public getOne(id: number): Observable<Competition | undefined> {
    // for the times, when endpoint is ready...
    // return this.httpClient.get<Competition>(`${environment.apiUrl}/${this.apiResource}/${id}`);

    console.log(id); // for lint

    const defaultLimits: Partial<{ [key in Country]: number }> = {};
    Object.values(Country).forEach((country) => {
      defaultLimits[country] = 0;
    });

    return of({
      id: 1,
      name: 'Sumo Showdown: Clash of Titans',
      startTime: new Date(2024, 7, 14),
      endTime: new Date(2024, 7, 15),
      countryLimits: defaultLimits as { [key in Country]: number },
      seasonId: 2,
      rank: CompetitionRank.European
    });
  }

  public add(ageCategory: Competition): Observable<Competition | undefined> {
    return this.httpClient.post<Competition>(
      `${environment.apiUrl}/${this.apiResource}`,
      ageCategory,
    );
  }

  public registerAthlete(
    competitionId: number,
    ageCategoryId: number,
    weightCategoryId: number,
    athleteId: number,
  ) {

    console.log(competitionId, ageCategoryId, weightCategoryId, athleteId); // for lint
    
    return true;
  }
}
