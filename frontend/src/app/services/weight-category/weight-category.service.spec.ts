import { TestBed } from '@angular/core/testing';

import { WeightCategoryService } from './weight-category.service';

describe('WeightCategoryService', () => {
  let service: WeightCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeightCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
