import { TestBed } from '@angular/core/testing';

import { WeightCategoryService } from './weight-category.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('WeightCategoryService', () => {
  let service: WeightCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]
    });
    service = TestBed.inject(WeightCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
