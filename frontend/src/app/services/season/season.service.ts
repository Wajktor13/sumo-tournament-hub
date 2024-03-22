import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Season } from '../../models/season';

@Injectable({
  providedIn: 'root',
})
export class SeasonService {
  constructor() {}

  public getAll(): Observable<Season[]> {
    return of([
      {
        id: 1,
        name: 'Spring 2024',
        startDate: new Date(2024, 2, 20),
        endDate: new Date(2024, 5, 20),
      },
      {
        id: 2,
        name: 'Summer 2024',
        startDate: new Date(2024, 5, 21),
        endDate: new Date(2024, 8, 22),
      },
    ]);
  }

  public getOne(id: number): Observable<Season | undefined> {
    return of({
      id: id,
      name: 'Spring 2024',
      startDate: new Date(2024, 2, 20),
      endDate: new Date(2024, 5, 20),
    });
  }
}
