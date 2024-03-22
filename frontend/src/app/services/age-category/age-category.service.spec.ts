import { TestBed } from '@angular/core/testing';

import { AgeCategoryService } from './age-category.service';

describe('AgeCategoryService', () => {
  let service: AgeCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AgeCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
