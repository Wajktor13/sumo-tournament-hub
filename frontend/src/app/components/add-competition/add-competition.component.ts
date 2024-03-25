import { Component } from '@angular/core';
import { FormGroup, Validators, FormControl, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { CompetitionRank } from '../../enums/competition-rank';
import { AgeCategoryName } from '../../enums/age-category-name';

@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrls: ['./add-competition.component.css']
})
export class AddCompetitionComponent {
  public competitionRanks = Object.values(CompetitionRank);
  public ageCategories = Object.values(AgeCategoryName);

  addCompetitionForm = new FormGroup({
    competitionName: new FormControl('', [
      Validators.required,
      Validators.pattern(/^[\p{L}\s\-.']+$/u)
    ]),
    startDate: new FormControl('', [
      Validators.required,
    ]),
    endDate: new FormControl('', [
      Validators.required
    ]),
    competitionRank: new FormControl('', [
      Validators.required
    ]),
    season: new FormControl('', [
      Validators.required
    ]),
    // Note: AgeCategories does not currently have validation.
    ageCategories: new FormControl()
  }, { validators: startDateBeforeEndDateValidator });

  
  // Note: The AgeCategories function is currently incorrect. 
  //Instead of returning which categories are selected,
  // it returns whether you selected or deselected any of the checkboxes as the last change.
  submitForm() {
    if (this.addCompetitionForm.valid) {
      console.log(this.addCompetitionForm.value);
    } else {
      console.log(this.addCompetitionForm.value);
      alert("Invalid input.");
    }
  }
}

const startDateBeforeEndDateValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const startDate = control.get('startDate');
  const endDate = control.get('endDate');

  if (startDate && endDate && startDate.value && endDate.value) {
    const startDateValue = new Date(startDate.value);
    const endDateValue = new Date(endDate.value);

    if (startDateValue >= endDateValue) {
      return { 'startDateAfterEndDate': true };
    }
  }

  return null;
};
