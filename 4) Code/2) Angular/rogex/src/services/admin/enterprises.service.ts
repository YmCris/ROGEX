import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Observable } from "rxjs";
import { UpdateEnterpriseRequest } from "../../models/enterprises/UpdateEnterpriseRequest";
import { Enterprise } from "../../models/enterprises/Enterprise";

@Injectable({
    providedIn: "root",
})
export class EnterpriseService {

    // VARIABLES ---------------------------------------------------------------
    restConstants = new RestConstants();

    // CONSTRUCTOR -------------------------------------------------------------
    constructor(private httpClient: HttpClient) { }

    // METHODS -----------------------------------------------------------------
    public createEnterprise(formValue: any): Observable<void> {
        return this.httpClient.post<void>(`${this.restConstants.getApiURL()}enterprises`, formValue);
    }

    public updateEnterprise(name: string, update: UpdateEnterpriseRequest): Observable<Enterprise> {
        return this.httpClient.put<Enterprise>(`${this.restConstants.getApiURL()}enterprises/${name}`, update);
    }

    public deleteEnterprise(name: string): Observable<void> {
        return this.httpClient.delete<void>(`${this.restConstants.getApiURL()}enterprises/${name}`);
    }

    public getEnterprise(name: string): Observable<Enterprise> {
        return this.httpClient.get<Enterprise>(`${this.restConstants.getApiURL()}enterprises/${name}`);
    }

    public getAllEnterprises(): Observable<Enterprise[]> {
        return this.httpClient.get<Enterprise[]>(`${this.restConstants.getApiURL()}enterprises`);
    }

}