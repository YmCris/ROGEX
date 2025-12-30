import { Injectable } from "@angular/core";
import { GenericService } from "../../common/crud-services/GenericService";
import { Videogame } from "../../models/videogames/Videogame";
import { HttpClient } from "@angular/common/http";
import { RestConstants } from "../../shared/restapi/rest-constants";

@Injectable({
    providedIn: "root",
})
export class VideogameService extends GenericService<Videogame> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'videogames',
            new RestConstants().getApiURL(),
        );
    }

}