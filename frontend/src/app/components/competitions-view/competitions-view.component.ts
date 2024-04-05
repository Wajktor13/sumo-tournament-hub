import { Component, OnDestroy, OnInit } from '@angular/core';
import { CompetitionService } from '../../services/competition/competition.service';
import { Competition } from '../../models/competition';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-competitions-view',
  standalone: false,
  templateUrl: './competitions-view.component.html',
  styleUrl: './competitions-view.component.css',
})
export class CompetitionsViewComponent implements OnInit, OnDestroy {
  public competitions: Competition[] = [];

  // subs
  public competitionSub: Subscription | undefined;

  constructor(
    private competitionsService: CompetitionService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.competitionSub = this.competitionsService.getAll().subscribe({
      next: (competitions: Competition[]) => (this.competitions = competitions),
      error: (e) => this.handleError(e),
    });
  }

  ngOnDestroy(): void {
    this.competitionSub?.unsubscribe();
  }

  private handleError(error: Error) {
    alert(error.message);
  }

  public navigateToRegistration(competitionId: number): void {
    this.router.navigate([`competition-registration/${competitionId}`]);
  }
}
