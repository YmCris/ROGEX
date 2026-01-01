import { HttpClient } from "@angular/common/http";
import { GenericService } from "../../common/crud-services/GenericService";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Injectable } from "@angular/core";
import { Sale } from "../../models/sale/sale";

@Injectable({
    providedIn: "root",
})
export class SaleService extends GenericService<Sale> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'sales',
            new RestConstants().getApiURL(),
        );
    }

}