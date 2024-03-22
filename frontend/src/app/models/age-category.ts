import { AgeCategoryName } from "../enums/age-category-name";
import { Gender } from "../enums/gender";
import { WeightCategory } from "./weight-category";

export type AgeCategory = {
    id: number;
    ageCategoryName: AgeCategoryName;
    ageLowerBound: number;
    ageUpperBound: number;
    gender: Gender;
    openWeightAvailable: boolean;
    weightCategories?: WeightCategory[];
};