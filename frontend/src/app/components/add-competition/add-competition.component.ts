import { Component } from '@angular/core';
import { CompetitionRank } from '../../enums/competition-rank';

@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrls: ['./add-competition.component.css']
})
export class AddCompetitionComponent {
  public competitionRanks = Object.values(CompetitionRank);
}
