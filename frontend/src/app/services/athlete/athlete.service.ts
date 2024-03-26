import { Injectable } from '@angular/core';
import { Athlete } from '../../models/athlete';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Gender } from '../../enums/gender';

@Injectable({
  providedIn: 'root',
})
export class AthleteService {
  private apiResource = 'athletes';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Athlete[]> {
    return this.httpClient.get<Athlete[]>(
      `${environment.apiUrl}/${this.apiResource}`,
    );
  }

  public getOne(id: number): Observable<Athlete | undefined> {
    return this.httpClient.get<Athlete>(
      `${environment.apiUrl}/${this.apiResource}/${id}`,
    );
  }

  public add(athlete: Athlete): Observable<Athlete | undefined> {
    return this.httpClient.post<Athlete>(
      `${environment.apiUrl}/${this.apiResource}`,
      athlete,
    );
  }

  public getAllByClubId(clubId: number) {
    return of([
      {
        id: 1,
        firstName: 'John',
        secondName: 'Doe',
        gender: Gender.M,
        birthdate: new Date(2003, 7, 7),
        clubId: clubId,
      },
      {
        id: 1,
        firstName: 'Hermiona',
        secondName: 'Johnson',
        gender: Gender.F,
        birthdate: new Date(1999, 3, 3),
        clubId: clubId,
      },
    ]);
  }
}
