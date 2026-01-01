import { HttpClient } from "@angular/common/http";
import { GenericService } from "../../common/crud-services/GenericService";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Injectable } from "@angular/core";
import { MemberGroup } from "../../models/groups/MemberGroup";

@Injectable({
    providedIn: "root",
})
export class MemberGroupService extends GenericService<MemberGroup> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'groups/members',
            new RestConstants().getApiURL(),
        );
    }

}