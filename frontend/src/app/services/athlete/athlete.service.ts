import { Injectable } from '@angular/core';
import { Athlete } from '../../models/athlete';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AthleteService {
  private apiResource = "athletes";

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Athlete[]> {
    return this.httpClient.get<Athlete[]>(`${environment.apiUrl}/${this.apiResource}`);
  }

  public getOne(id: number): Observable<Athlete | undefined> {
    return this.httpClient.get<Athlete>(`${environment.apiUrl}/${this.apiResource}/${id}`);
  }

  public add(athlete: Athlete): Observable<Athlete | undefined> {    
    return this.httpClient.post<Athlete>(`${environment.apiUrl}/${this.apiResource}`, athlete);
  }
}
