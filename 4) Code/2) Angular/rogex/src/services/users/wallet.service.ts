import { HttpClient } from "@angular/common/http";
import { GenericService } from "../../common/crud-services/GenericService";
import { Wallet } from "../../models/wallet/Wallet";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: "root",
})
export class WalletService extends GenericService<Wallet> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'wallets',
            new RestConstants().getApiURL(),
        );
    }

}