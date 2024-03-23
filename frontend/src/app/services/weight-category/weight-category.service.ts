import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { WeightCategory } from '../../models/weight-category';

@Injectable({
  providedIn: 'root',
})
export class WeightCategoryService {
  private apiResource = "weightcategories";

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<WeightCategory[]> {
    return this.httpClient.get<WeightCategory[]>(`${environment.apiUrl}/${this.apiResource}`);
  }

  public getOne(id: number): Observable<WeightCategory | undefined> {
    return this.httpClient.get<WeightCategory>(`${environment.apiUrl}/${this.apiResource}/${id}`);
  }

  public add(ageCategory: WeightCategory): Observable<WeightCategory | undefined> {    
    return this.httpClient.post<WeightCategory>(`${environment.apiUrl}/${this.apiResource}`, ageCategory);
  }
}
