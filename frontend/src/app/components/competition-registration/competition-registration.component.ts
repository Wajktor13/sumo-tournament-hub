import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Competition } from '../../models/competition';
import { CompetitionService } from '../../services/competition/competition.service';
import { Season } from '../../models/season';
import { SeasonService } from '../../services/season/season.service';
import { Subscription } from 'rxjs';
import { Club } from '../../models/club';
import { AuthService } from '../../services/auth/auth.service';
import { ClubService } from '../../services/club/club.service';
import { User } from '../../models/user';
import { Athlete } from '../../models/athlete';
import { AgeCategory } from '../../models/age-category';
import { AgeCategoryService } from '../../services/age-category/age-category.service';
import { WeightCategory } from '../../models/weight-category';
import { RegistrationService } from '../../services/registration/registration.service';
import { Registration } from '../../models/registration';

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
  public selectedWeightCategoryId: number | undefined;

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
    private ageCategoryService: AgeCategoryService,
    private registartionService: RegistrationService,
  ) {}

  ngOnInit(): void {
    const routeParams: ParamMap = this.route.snapshot.paramMap;
    const competitionId: number = Number(routeParams.get('competitionId'));

    this.subscribeToCompetitionService(competitionId);
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
          if (competition != undefined && competition.startTime <= new Date()) {
            alert('this competition expired');
            this.router.navigate(['/competitions-view']);
          }

          this.competition = competition;
          this.subscribeToSeasonService();
        },
        error: (e) => this.router.navigate(['/page-not-found']),
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
      this.athletesFromClubSub = this.clubService
        .getAllAthletes(this.selectedClubId)
        .subscribe({
          next: (athletes: Athlete[] | undefined) => {
            this.athletesFromClub = athletes;
          },
          error: (e) => this.handleError(e),
        });
    }
  }

  public onAthleteSelectionChange() {
    this.selectedAthleteAgeCategorySub?.unsubscribe;

    if (this.selectedAthleteId != undefined && this.season != undefined) {
      this.selectedAthleteAgeCategorySub = this.ageCategoryService
        .getAgeCategoryByAthleteIdAndSeasonId(
          this.selectedAthleteId,
          this.season.id,
        )
        .subscribe({
          next: (ageCategory: AgeCategory | undefined) => {
            this.selectedAthleteAgeCategory = ageCategory;
            this.subscribeToCategoryWeightService();
          },
          error: (e) => this.handleError(e),
        });
    }
  }

  private subscribeToCategoryWeightService() {
    this.availableWeightCategoriesSub?.unsubscribe;

    if (this.selectedAthleteAgeCategory == undefined) {
      return;
    }

    this.availableWeightCategoriesSub = this.ageCategoryService
      .getAllWeightCategories(this.selectedAthleteAgeCategory.id)
      .subscribe({
        next: (weightCategories: WeightCategory[] | undefined) =>
          (this.availableWeightCategories = weightCategories?.sort(
            (wc1: WeightCategory, wc2: WeightCategory) => {
              const wc1_ul: number = wc1.weightUpperLimit;
              const wc2_ul: number = wc2.weightUpperLimit;

              if (wc1_ul != -1 && wc2_ul != -1) {
                return wc1_ul - wc2_ul;
              } else if (wc1_ul == -1) {
                return 1;
              } else {
                return -1;
              }
            },
          )),
        error: (e) => this.handleError(e),
      });
  }

  public onButtonClick() {
    if (
      this.competition != undefined &&
      this.selectedAthleteAgeCategory != undefined &&
      this.selectedWeightCategoryId != undefined &&
      this.selectedAthleteId
    ) {
      const registration: Partial<Registration> = {
        athleteId: this.selectedAthleteId,
        weightCategoryId: this.selectedWeightCategoryId,
        registrationDate: new Date().toISOString(),
      };

      this.registartionService.addRegistration(registration);

      console.log(registration);

      alert('successfully registered');
    } else {
      alert('an error occured');
    }
  }

  private handleError(error: Error) {
    alert(error.message);
  }
}
