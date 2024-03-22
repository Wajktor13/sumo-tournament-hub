import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Competition } from '../../models/competition';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CompetitionService {
  private apiResource = "competitions";

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<Competition[]> {
    return this.httpClient.get<Competition[]>(`${environment.apiUrl}/${this.apiResource}`);
  }

  public getOne(id: number): Observable<Competition | undefined> {
    return this.httpClient.get<Competition>(`${environment.apiUrl}/${this.apiResource}/${id}`);
  }

  public add(ageCategory: Competition): Observable<Competition | undefined> {    
    return this.httpClient.post<Competition>(`${environment.apiUrl}/${this.apiResource}`, ageCategory);
  }
}
