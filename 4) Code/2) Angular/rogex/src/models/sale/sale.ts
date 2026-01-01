import { BanckType } from "../wallet/BanckType";

export interface Sale {
    saleDate: Date;
    userEmail: string;
    videogameTitle: string;
    enterpriseName: string;

    walletName: string;
    walletBanck: BanckType;
}