import { TestBed } from '@angular/core/testing';

import { AgeCategoryService } from './age-category.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AgeCategoryService', () => {
  let service: AgeCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]
    });
    service = TestBed.inject(AgeCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
