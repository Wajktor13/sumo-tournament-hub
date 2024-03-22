import { Injectable } from '@angular/core';
import { Club } from '../../models/club';
import { Observable, of } from 'rxjs';
import { Country } from '../../enums/country';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root',
})
export class ClubService {
  constructor() {}

  public getAll(): Observable<Club[]> {
    return of([
      {
        id: 0,
        name: 'Club 1',
        country: Country.AFGHANISTAN,
      },
      {
        id: 1,
        name: 'Club 2',
        country: Country.POLAND,
      },
    ]);
  }

  public getOne(id: number): Observable<Club | undefined> {
    return of({
      id: id,
      name: 'Club 1',
      country: Country.AFGHANISTAN,
    });
  }

  public getAllClubsByUser(user: User): Observable<Club[]> {
    console.log(user);

    return of([
      {
        id: 0,
        name: 'Club 1',
        country: Country.AFGHANISTAN,
      },
      {
        id: 1,
        name: 'Club 2',
        country: Country.POLAND,
      },
    ]);
  }
}
