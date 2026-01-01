import { HttpClient } from "@angular/common/http";
import { GenericService } from "../../common/crud-services/GenericService";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Injectable } from "@angular/core";
import { Invitation } from "../../models/invitations/Invitation";

@Injectable({
    providedIn: "root",
})
export class InvitationService extends GenericService<Invitation> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'invitations',
            new RestConstants().getApiURL(),
        );
    }

}