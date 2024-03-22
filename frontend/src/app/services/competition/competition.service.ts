import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Competition } from '../../models/competition';

@Injectable({
  providedIn: 'root',
})
export class CompetitionService {
  constructor() {}

  public getAll(): Observable<Competition[]> {
    return of([
      {
        id: 1,
        name: 'Mock Competition 1',
        startTime: new Date(),
        endTime: new Date(),
        countryLimits: { USA: 10, UK: 15 },
        seasonId: 0
      },
      {
        id: 2,
        name: 'Mock Competition 2',
        startTime: new Date(),
        endTime: new Date(),
        countryLimits: { USA: 20, UK: 25 },
        seasonId: 1
      },
    ]);
  }

  public getOne(id: number): Observable<Competition | undefined> {
    return of({
      id: id,
      name: 'Mock Competition 1',
      startTime: new Date(),
      endTime: new Date(),
      countryLimits: { USA: 10, UK: 15 },
      seasonId: 0
    });
  }
}
