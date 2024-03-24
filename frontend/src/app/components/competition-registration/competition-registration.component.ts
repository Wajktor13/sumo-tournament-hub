import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Competition } from '../../models/competition';
import { CompetitionService } from '../../services/competition/competition.service';
import { Season } from '../../models/season';
import { SeasonService } from '../../services/season/season.service';
import { Subscription } from 'rxjs';
import { Club } from '../../models/club';
import { AuthService } from '../../services/auth/auth.service';
import { ClubService } from '../../services/club/club.service';
import { User } from '../../models/user';
import { AthleteService } from '../../services/athlete/athlete.service';
import { Athlete } from '../../models/athlete';
import { AgeCategory } from '../../models/age-category';
import { AgeCategoryService } from '../../services/age-category/age-category.service';
import { WeightCategoryService } from '../../services/weight-category/weight-category.service';
import { WeightCategory } from '../../models/weight-category';

@Component({
  selector: 'app-competition-registration',
  standalone: false,
  templateUrl: './competition-registration.component.html',
  styleUrl: './competition-registration.component.css',
})
export class CompetitionRegistrationComponent implements OnInit, OnDestroy {
  public competition: Competition | undefined;
  public season: Season | undefined;
  public isLoggedIn: boolean = false;
  public currentUser: User | undefined;
  public userClubs: Club[] | undefined;
  public athletesFromClub: Athlete[] | undefined;
  public selectedClubId: number | undefined;
  public selectedAthleteId: number | undefined;
  public selectedAthleteAgeCategory: AgeCategory | undefined;
  public availableWeightCategories: WeightCategory[] | undefined;
  public selectedWeightCategory: WeightCategory | undefined;

  // subs
  private competitionSub: Subscription | undefined;
  private seasonSub: Subscription | undefined;
  private isLoggedInSub: Subscription | undefined;
  private currentUserSub: Subscription | undefined;
  private userClubsSub: Subscription | undefined;
  private athletesFromClubSub: Subscription | undefined;
  private selectedAthleteAgeCategorySub: Subscription | undefined;
  private availableWeightCategoriesSub: Subscription | undefined;

  constructor(
    private route: ActivatedRoute,
    private competitionService: CompetitionService,
    private seasonService: SeasonService,
    private authService: AuthService,
    private clubService: ClubService,
    private router: Router,
    private athleteService: AthleteService,
    private ageCategoryService: AgeCategoryService,
    private weightCategoryService: WeightCategoryService,
  ) {}

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    const competitionId = Number(routeParams.get('competitionId'));

    this.subscribeToCompetitionService(competitionId);
    this.subscribeToSeasonService();
    this.subscribeToUserService();
    this.subscribeToClubService();
  }

  ngOnDestroy(): void {
    this.competitionSub?.unsubscribe();
    this.seasonSub?.unsubscribe();
    this.currentUserSub?.unsubscribe();
    this.userClubsSub?.unsubscribe();
    this.isLoggedInSub?.unsubscribe();
    this.athletesFromClubSub?.unsubscribe();
  }

  private subscribeToCompetitionService(competitionId: number) {
    this.competitionSub = this.competitionService
      .getOne(competitionId)
      .subscribe({
        next: (competition: Competition | undefined) => {
          this.competition = competition;

          if (competition != undefined && competition.startTime <= new Date()) {
            alert('this competition expired');
            this.router.navigate(['/home']); // should be changed to subpage with competitions
          }
        },
        error: (e) => this.handleError(e),
      });
  }

  private subscribeToSeasonService() {
    if (this.competition != undefined) {
      this.seasonSub = this.seasonService
        .getOne(this.competition.seasonId)
        .subscribe({
          next: (season: Season | undefined) => (this.season = season),
          error: (e) => this.handleError(e),
        });
    }
  }

  private subscribeToUserService() {
    this.currentUserSub = this.authService.currentUser$.subscribe({
      next: (user: User | undefined) => (this.currentUser = user),
      error: (e) => this.handleError(e),
    });

    this.isLoggedInSub = this.authService.isLoggedIn$.subscribe({
      next: (isLoggedIn: boolean) => {
        this.isLoggedIn = isLoggedIn;

        if (!isLoggedIn) {
          alert(
            'you need to log in in order to register athletes for competition',
          );
          this.router.navigate(['/login']);
        }
      },
      error: (e) => this.handleError(e),
    });
  }

  private subscribeToClubService() {
    if (this.currentUser != undefined) {
      this.userClubsSub = this.clubService
        .getAllByUser(this.currentUser)
        .subscribe({
          next: (clubs: Club[] | undefined) => (this.userClubs = clubs),
          error: (e) => this.handleError(e),
        });
    }
  }

  public onClubSelectionChange() {
    this.athletesFromClubSub?.unsubscribe();

    if (this.selectedClubId != undefined) {
      this.athletesFromClubSub = this.athleteService
        .getAllByClubId(this.selectedClubId)
        .subscribe({
          next: (athletes: Athlete[] | undefined) =>
            (this.athletesFromClub = athletes),
          error: (e) => this.handleError(e),
        });
    }
  }

  public onAthleteSelectionChange() {
    this.selectedAthleteAgeCategorySub?.unsubscribe;

    if (this.selectedAthleteId != undefined) {
      this.selectedAthleteAgeCategorySub = this.ageCategoryService
        .getByAthleteId(this.selectedAthleteId)
        .subscribe({
          next: (ageCategory: AgeCategory | undefined) =>
            (this.selectedAthleteAgeCategory = ageCategory),
          error: (e) => this.handleError(e),
        });
    }

    this.availableWeightCategoriesSub?.unsubscribe();

    if (
      this.selectedAthleteAgeCategory != undefined &&
      this.competition != undefined
    ) {
      this.availableWeightCategoriesSub = this.weightCategoryService
        .getAllByAgeCategoryIdAndCompetitionId(
          this.selectedAthleteAgeCategory.id,
          this.competition.id,
        )
        .subscribe({
          next: (weightCategories: WeightCategory[] | undefined) =>
            (this.availableWeightCategories = weightCategories),
          error: (e) => this.handleError(e),
        });
    }
  }

  public onButtonClick() {
    if (
      this.competition != undefined &&
      this.selectedAthleteAgeCategory != undefined &&
      this.selectedWeightCategory != undefined &&
      this.selectedAthleteId &&
      this.competitionService.registerAthlete(
        this.competition?.id,
        this.selectedAthleteAgeCategory.id,
        this.selectedWeightCategory.id,
        this.selectedAthleteId,
      )
    ) {
      alert('successfully registered');
    } else {
      alert('an error occured');
    }
  }

  private handleError(error: Error) {
    alert(error.message);
  }
}
