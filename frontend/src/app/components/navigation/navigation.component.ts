import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  standalone: false,
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css',
})
export class NavigationComponent {
  constructor(private router: Router) {}

  public navigateTo(relativePath: string) {
    this.router.navigate([relativePath]);
  }
}
