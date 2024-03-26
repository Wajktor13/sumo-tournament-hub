import { Injectable } from '@angular/core';
import { Club } from '../../models/club';
import { Observable, of } from 'rxjs';
import { User } from '../../models/user';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Country } from '../../enums/country';

@Injectable({
  providedIn: 'root',
})
export class ClubService {
  private apiResource = 'clubs';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Club[]> {
    return this.httpClient.get<Club[]>(
      `${environment.apiUrl}/${this.apiResource}`,
    );
  }

  public getOne(id: number): Observable<Club | undefined> {
    return this.httpClient.get<Club>(
      `${environment.apiUrl}/${this.apiResource}/${id}`,
    );
  }

  public getAllByUser(user: User): Observable<Club[]> {
    // will be changed when endpoint is ready
    // return this.httpClient.get<Club[]>(`${environment.apiUrl}/${this.apiResource}`);

    console.log(user); // for lint

    return of([
      {
        id: 1,
        name: 'Czerwony Smok',
        country: Country.POLAND,
      },
      {
        id: 2,
        name: 'Spartakus',
        country: Country.GERMANY,
      },
    ]);
  }
}
