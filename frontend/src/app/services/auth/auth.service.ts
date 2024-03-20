import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  public isLoggedIn$: BehaviorSubject<boolean> = new BehaviorSubject(false);
  public currentUser$: BehaviorSubject<User> = new BehaviorSubject({} as User);

  constructor() {}

  public login(email: string, password: string): void {
    const user: User = this.authenticate(email, password);

    this.currentUser$.next(user);
    this.isLoggedIn$.next(true);
  }

  private authenticate(email: string, password: string): User {
    console.log(`Authenticating ${email} + ${password}...`);

    return {
      id: 0,
      email: 'superuser@mail.com',
      firstName: 'Super',
      lastName: 'User',
      roles: {
        admin: true,
        staffCoach: true,
        coach: true,
      },
    } as User;
  }

  public logout(): void {
    this.currentUser$.next({} as User);
    this.isLoggedIn$.next(false);
  }
}
