import { BanckType } from "./BanckType";

export interface Wallet {

    userEmail: string;
    name: string;
    fund: number;
    banck: BanckType;

}