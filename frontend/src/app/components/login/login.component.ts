import { Component, OnDestroy } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { User } from '../../models/user';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnDestroy {
  public email: string = '';
  public password: string = '';
  public isLoggedIn: boolean = false;
  public user: User = {} as User;
  public isLoggedInSub: Subscription | null = null;
  public userSub: Subscription | null = null;

  ngOnDestroy(): void {
    this.isLoggedInSub?.unsubscribe();
    this.userSub?.unsubscribe();
  }

  constructor(private authService: AuthService) {
    this.authService.isLoggedIn$.subscribe({
      next: (value) => (this.isLoggedIn = value),
      error: (e) => console.log(e),
    });

    this.authService.currentUser$.subscribe({
      next: (value) => (this.user = value),
      error: (e) => console.log(e),
    });
  }

  public login(): void {
    this.authService.login(this.email, this.password);
    alert("successfully logged in");
  }
}
