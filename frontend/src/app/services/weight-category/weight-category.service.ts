import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { WeightCategory } from '../../models/weight-category';

@Injectable({
  providedIn: 'root',
})
export class WeightCategoryService {
  constructor() {}

  public getAll(): Observable<WeightCategory[]> {
    return of([
      {
        id: 0,
        weightUpperLimit: 60,
      },
      {
        id: 1,
        weightUpperLimit: 100,
      },
    ]);
  }

  public getOne(id: number): Observable<WeightCategory | undefined> {
    return of({
      id: id,
      weightUpperLimit: 60,
    });
  }
}
