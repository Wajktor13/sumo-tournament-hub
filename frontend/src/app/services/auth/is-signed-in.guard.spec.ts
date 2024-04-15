import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { IsSignedInGuard } from './is-signed-in.guard';

describe('IsSignedInGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => IsSignedInGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
