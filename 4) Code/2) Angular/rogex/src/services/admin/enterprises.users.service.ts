import { Injectable } from "@angular/core";
import { GenericService } from "../../common/crud-services/GenericService";
import { EnterpriseUsers } from "../../models/enterprise-users/EnterpriseUser";
import { HttpClient } from "@angular/common/http";
import { RestConstants } from "../../shared/restapi/rest-constants";

@Injectable({
    providedIn: "root",
})
export class EnterpriseUsersService extends GenericService<EnterpriseUsers> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            "users/enterprises",
            new RestConstants().getApiURL(),
        );
    }

}