import { HttpClient } from "@angular/common/http";
import { GenericService } from "../../common/crud-services/GenericService";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { VideogameMultimedia } from "../../models/videogames-multimedia/VideogameMultimedia";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class VideogameMultimediaService extends GenericService<VideogameMultimedia> {

    // CONSTRUCTOR -------------------------------------------------------------
    constructor(
        httpClient: HttpClient,
    ) {
        super(
            httpClient,
            'videogames/multimedia',
            new RestConstants().getApiURL(),

        );
    }
    
    createMultipart(formData: FormData): Observable<void> {

        return this.httpClient.post<void>(
            `${this.apiBaseUrl}${this.resource}`,
            formData
        );
    }


}