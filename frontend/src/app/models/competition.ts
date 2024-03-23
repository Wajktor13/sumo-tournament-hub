export type Competition = {
  id: number;
  name: string;
  startTime: Date;
  endTime: Date;
  countryLimits: { [key: string]: number };
  seasonId: number;
};
