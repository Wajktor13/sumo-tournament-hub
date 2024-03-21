import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-athlete',
  standalone: false,
  templateUrl: './add-athlete.component.html',
  styleUrl: './add-athlete.component.css'
})
export class AddAthleteComponent implements OnInit {
  date?: string;

  ngOnInit() {
    this.date = new Date().toISOString().slice(0, 10);
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
    club: new FormControl('', [
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
    console.log(this.addAthleteForm.valid);
  }
}
