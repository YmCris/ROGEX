import { AgeRating } from "./AgeRating";

export interface Videogame {

    title: string;//<-PK
    description: string;
    price: number;
    minimumRequirements: string;
    ageRating: AgeRating;
    enterpriseName: string;//<-PK
    suspensionOfSale: boolean;
    hiddenComments: boolean;
    hidden: boolean;

}