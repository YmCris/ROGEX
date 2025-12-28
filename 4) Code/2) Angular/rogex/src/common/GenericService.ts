import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { GenericUpdateObjectRequest } from "./GenericUpdateObjectRequest";
import { RestConstants } from "../shared/restapi/rest-constants";

/**
 * Use 
 * @Injectable({
 *      providedIn: "root",
 *  })
 * 
 * SOLO USAR CUANDO LOS PKS SEAN PATH PARAMETERS DE LO CONTRARIO CREAR LA
 * VARIANTE con HttpParams
 */
export class GenericService<T> {

    // VARIABLES ---------------------------------------------------------------
    private restConstants = new RestConstants();
    private API_BASE_URL: string = this.restConstants.getApiURL();

    // CONSTRUCTOR -------------------------------------------------------------
    constructor(
        protected httpClient: HttpClient,
        protected resource: string
    ) { }

    // METHODS -----------------------------------------------------------------
    public createObject(formValue: T): Observable<void> {
        return this.httpClient.post<void>(
            `${this.API_BASE_URL}${this.resource}`,
            formValue
        );
    }

    public updateObject(primaryKeys: string[],
        update: GenericUpdateObjectRequest): Observable<T> {

        const path = primaryKeys.join("/");

        return this.httpClient.put<T>(
            `${this.API_BASE_URL}${this.resource}/${path}`,
            update
        );
    }

    public deleteWithPrimaryKeys(primaryKeys: string[]): Observable<void> {

        const path = primaryKeys.join("/");

        return this.httpClient.delete<void>(
            `${this.API_BASE_URL}${this.resource}/${path}`
        );
    }

    public getByPrimaryKeys(primaryKeys: string[]): Observable<T> {

        const path = primaryKeys.join("/");

        return this.httpClient.get<T>(
            `${this.API_BASE_URL}${this.resource}/${path}`
        );

    }

    public getAllObjects(): Observable<T[]> {
        return this.httpClient.get<T[]>(
            `${this.API_BASE_URL}${this.resource}`
        );
    }

}