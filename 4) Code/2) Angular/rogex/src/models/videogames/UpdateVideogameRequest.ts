import { GenericUpdateObjectRequest } from "../../common/dtos/GenericUpdateObjectRequest";
import { AgeRating } from "./AgeRating";

export interface UpdateVideogameRequest extends GenericUpdateObjectRequest {
    description: string;
    price: number;
    minimumRequirements: string;
    ageRating: AgeRating;

    suspensionOfSale: boolean | null;
    hiddenComments: boolean | null;
    hidden: boolean | null;

    newCategory: string | null;
    existingCategory: string | null;

}