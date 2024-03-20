import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Competition } from '../../models/competition';

@Injectable({
  providedIn: 'root',
})
export class CompetitionService {
  public competitions$: BehaviorSubject<Competition[]> = new BehaviorSubject(
    [] as Competition[],
  );

  constructor() {
    this.fetchCompetitions();
  }

  private fetchCompetitions(): void {
    const competitionsMock: Competition[] = [
      {
        id: 1,
        name: 'Mock Competition 1',
        startTime: new Date(),
        endTime: new Date(),
        countryLimits: { USA: 10, UK: 15 },
      },
      {
        id: 2,
        name: 'Mock Competition 2',
        startTime: new Date(),
        endTime: new Date(),
        countryLimits: { USA: 20, UK: 25 },
      },
    ];

    this.competitions$.next(competitionsMock);
  }
}
