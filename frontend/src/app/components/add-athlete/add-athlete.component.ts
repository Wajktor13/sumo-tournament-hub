import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ClubService } from '../../services/club/club.service';
import { AthleteService } from '../../services/athlete/athlete.service';
import { AuthService } from '../../services/auth/auth.service';
import { Club } from '../../models/club';
import { Athlete } from '../../models/athlete';
import { User } from '../../models/user';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-add-athlete',
  standalone: false,
  templateUrl: './add-athlete.component.html',
  styleUrl: './add-athlete.component.css'
})
export class AddAthleteComponent implements OnInit, OnDestroy {
  date?: string;
  availableClubs: Club[] = [];

  // subs
  currentUserSub: Subscription | undefined;
  availableClubsSub: Subscription | undefined;

  constructor (private clubService: ClubService, private authService: AuthService, private athleteService: AthleteService) { }

  ngOnInit() {
    this.date = new Date().toISOString().slice(0, 10);

    this.currentUserSub = this.authService.currentUser$.subscribe(
      {
        next: (user: User) => 
        {
          this.availableClubsSub = this.clubService.getAllByUser(user).subscribe(
            {
              next: (clubs: Club[]) => this.availableClubs = clubs,
              error: e => console.log(e)
            }
          )
        },
        error: e => console.log(e)
      }
    )
  }

  ngOnDestroy(): void {
    this.currentUserSub?.unsubscribe();
    this.availableClubsSub?.unsubscribe();
  }

  addAthleteForm = new FormGroup({
    firstName: new FormControl('', [
      Validators.required,
      Validators.pattern(/^[\p{L}\s\-.']+$/u)
    ]),
    secondName: new FormControl('', [
      Validators.required,
      Validators.pattern(/^[\p{L}\s\-.']+$/u)
    ]),
    clubId: new FormControl('', [
      Validators.required
    ]),
    gender: new FormControl('', [
      Validators.required
    ]),
    birthDate: new FormControl('', [
      Validators.required
    ])
  })

  submitForm() {
    if (this.addAthleteForm.valid) {
      const data: any = this.addAthleteForm.value;
      
      const athlete: Athlete = {
        id: 0,
        firstName: data.firstName,
        secondName: data.secondName,
        gender: data.gender,
        birthdate: new Date(data.birthDate),
        clubId: parseInt(data.clubId)
      }
      
      this.athleteService.add(athlete).subscribe(
        {
          next: v => console.log(v),
          error: e => console.log(e)  
        }
      )
      
    } else {
      alert("invalid input")
    }
  }
}
