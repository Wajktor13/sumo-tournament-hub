import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { Subscription } from 'rxjs';
import { AuthService } from '../../services/auth/auth.service';
import { UserRoles } from '../../models/user-roles';

@Component({
  selector: 'app-navigation',
  standalone: false,
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css',
})
export class NavigationComponent implements OnDestroy {
  public isLoggedIn: boolean = false;
  public user: User = {} as User;
  public isLoggedInSub: Subscription | null = null;
  public userSub: Subscription | null = null;

  constructor(
    private router: Router,
    private authService: AuthService,
  ) {
    this.authService.isLoggedIn$.subscribe({
      next: (value) => (this.isLoggedIn = value),
      error: (e) => console.log(e),
    });

    this.authService.currentUser$.subscribe({
      next: (value) => (this.user = value),
      error: (e) => console.log(e),
    });
  }

  ngOnDestroy(): void {
    this.isLoggedInSub?.unsubscribe();
    this.userSub?.unsubscribe();
  }

  public navigateTo(relativePath: string): void {
    this.router.navigate([relativePath]);
  }

  public logout(): void {
    this.authService.logout();
  }

  public getRoleString(): string {
    const roles: UserRoles = this.user.roles;
    let roleString: string = '';

    if (roles.admin) {
      roleString += 'Admin + ';
    }

    if (roles.staffCoach) {
      roleString += 'Staff Coach + ';
    }

    if (roles.coach) {
      roleString += 'Coach';
    }

    if (roleString.endsWith(' + ')) {
      roleString = roleString.slice(0, roleString.length - 4);
    }

    return roleString;
  }
}
