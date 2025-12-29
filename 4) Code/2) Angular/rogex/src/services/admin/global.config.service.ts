import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { GlobalConfig } from "../../models/global-config/GlobalConfig";
import { Observable } from "rxjs";
import { UpdateSystemRequest } from "../../models/global-config/UpdateSystemRequest";

@Injectable({
    providedIn: "root",
})
export class GlobalConfigService {

    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public getConfig(): Observable<GlobalConfig> {
        return this.httpClient.get<GlobalConfig>(`${this.restConstants.getApiURL()}system/config`);
    }

    public updateConfig(update : UpdateSystemRequest): Observable<GlobalConfig> {
        return this.httpClient.put<GlobalConfig>(`${this.restConstants.getApiURL()}system/config`,update);
    }

    
}