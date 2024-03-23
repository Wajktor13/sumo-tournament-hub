import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AgeCategory } from '../../models/age-category';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AgeCategoryService {
  private apiResource = "agecategories";

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<AgeCategory[]> {
    return this.httpClient.get<AgeCategory[]>(`${environment.apiUrl}/${this.apiResource}`);
  }

  public getOne(id: number): Observable<AgeCategory | undefined> {
    return this.httpClient.get<AgeCategory>(`${environment.apiUrl}/${this.apiResource}/${id}`);
  }

  public add(ageCategory: AgeCategory): Observable<AgeCategory | undefined> {    
    return this.httpClient.post<AgeCategory>(`${environment.apiUrl}/${this.apiResource}`, ageCategory);
  }
}