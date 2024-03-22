import { TestBed } from '@angular/core/testing';

import { SeasonService } from './season.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('SeasonService', () => {
  let service: SeasonService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]
    });
    service = TestBed.inject(SeasonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
