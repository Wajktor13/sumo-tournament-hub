import { Injectable } from '@angular/core';
import { Athlete } from '../../models/athlete';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AthleteService {
  constructor() {}

  public getAll(): Observable<Athlete[]> {
    return of([
      {
        id: 1,
        firstName: 'John',
        secondName: 'Doe',
        gender: 'M',
        birthDate: new Date(1990, 5, 15),
      },
      {
        id: 2,
        firstName: 'Jane',
        secondName: 'Smith',
        gender: 'F',
        birthDate: new Date(1985, 8, 25),
      },
      {
        id: 3,
        firstName: 'Michael',
        secondName: 'Johnson',
        gender: 'M',
        birthDate: new Date(1988, 3, 10),
      },
    ]);
  }

  public getOne(id: number): Observable<Athlete | undefined> {
    return of({
      id: id,
      firstName: 'John',
      secondName: 'Doe',
      gender: 'M',
      birthDate: new Date(1990, 5, 15),
    });
  }
}
