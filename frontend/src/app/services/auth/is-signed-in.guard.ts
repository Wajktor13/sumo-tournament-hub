import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export const IsSignedInGuard: CanActivateFn = ():
  Observable<boolean | UrlTree> 
  | Promise<boolean | UrlTree> 
  | boolean 
  | UrlTree => {

  return inject(AuthService).isLoggedIn$.value
    ? inject(Router).createUrlTree(['/home'])
    : true

};