import { Gender } from "../enums/gender";

export type Athlete = {
  id: number;
  firstName: string;
  secondName: string;
  gender: Gender;
  birthDate: Date;
};
