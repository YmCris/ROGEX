import { HttpClient } from "@angular/common/http";
import { GenericService } from "../../common/crud-services/GenericService";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Injectable } from "@angular/core";
import { Group } from "../../models/groups/Group";

@Injectable({
    providedIn: "root",
})
export class GroupService extends GenericService<Group> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'groups',
            new RestConstants().getApiURL(),
        );
    }

}