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

  ngOnDestroy(): void {
    this.isLoggedInSub?.unsubscribe();
    this.userSub?.unsubscribe();
  }

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

  public navigateTo(relativePath: string) {
    this.router.navigate([relativePath]);
  }

  public logout() {
    this.authService.logout();
  }

  public getRoleString() {
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
