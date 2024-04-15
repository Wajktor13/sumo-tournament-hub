import { CompetitionRank } from '../enums/competition-rank';
import { Country } from '../enums/country';

export type Competition = {
  id: number;
  name: string;
  startTime: Date;
  endTime: Date;
  countryLimits: { [key in Country]: number } | {};
  seasonId: number;
  rank: CompetitionRank;
};
