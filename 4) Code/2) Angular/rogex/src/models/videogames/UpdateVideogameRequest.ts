import { GenericUpdateObjectRequest } from "../../common/dtos/GenericUpdateObjectRequest";
import { AgeRating } from "./AgeRating";

export interface UpdateVideogameRequest extends GenericUpdateObjectRequest {
    description: string | null;
    price: number | null;
    minimumRequirements: string | null;
    ageRating: AgeRating | null;

    suspensionOfSale: boolean | null;
    hiddenComments: boolean | null;
    hidden: boolean | null;

    newCategory: string | null;
    existingCategory: string | null;

}