import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { GenericUpdateObjectRequest } from "../dtos/GenericUpdateObjectRequest";

/** 
 * GenericService is the class responsible of be the fatter class of all services
 * in the program
 * 
 * Note: Only use this with the pks be "PathParam" and implement 
 * @Injectable({providedIn: "root",})
 */
export abstract class GenericService<T> {

    // CONSTRUCTOR -------------------------------------------------------------
    /**
     * Constructor method important to initialize the api rout (This can't be
     * a constant because it's a service that connect with the resource)
     * 
     * @param httpClient httpClient to use the API
     * @param resource the "@Path("...")" that the backend manage to the resource
     * @param apiBaseUrl The base URL "localhost:8080/xxxx/api/v1/"
     */
    constructor(
        protected httpClient: HttpClient,
        protected resource: string,
        protected apiBaseUrl: string
    ) { }

    // METHODS -----------------------------------------------------------------
    /**
     * Method responsible for send the forma data to backend to create the object
     * in the DB
     * 
     * @param formValue Form that contains the data form
     * @returns Observable, this returns some message about what happened and
     * the response code 
     */
    public createObject(formValue: T): Observable<void> {
        console.log("Creating object");

        return this.httpClient.post<void>(
            `${this.apiBaseUrl}${this.resource}`,
            formValue
        );
    }

    /**
     * Method responsible for update and object with the pks and the data to update
     * It works like a patch but is a put
     * 
     * @param primaryKeys Primary keys to access to the specific object
     * @param update Update request (It have to be exactly like is in the backend)
     * @returns The object modified
     */
    public updateObject(primaryKeys: string[],
        update: GenericUpdateObjectRequest): Observable<T> {

        console.log("Updating object");

        const path = primaryKeys.join("/");

        return this.httpClient.put<T>(
            `${this.apiBaseUrl}${this.resource}/${path}`,
            update
        );
    }

    /**
     * Method responsible for delete an object in the DB, with the primary keys
     * 
     * @param primaryKeys Primary keys to access to the specific object
     * @returns Message indicating what happened
     */
    public deleteWithPrimaryKeys(primaryKeys: string[]): Observable<void> {

        console.log("Deleting object with pks");

        const path = primaryKeys.join("/");

        return this.httpClient.delete<void>(
            `${this.apiBaseUrl}${this.resource}/${path}`
        );
    }

    /**
     * Method responsible for get an object with their primary keys.
     * 
     * @param primaryKeys Primary keys to access to the object
     * @returns Object founded or a message indicating what happened
     */
    public getByPrimaryKeys(primaryKeys: string[]): Observable<T> {

        console.log("Getting some entity by pks");

        const path = primaryKeys.join("/");

        return this.httpClient.get<T>(
            `${this.apiBaseUrl}${this.resource}/${path}`
        );

    }

    /**
     * Method responsible for get all objects with some restriction or conditions
     * This is the pks array
     * 
     * @param primaryKeys Conditions or restrictions to get some response
     * @returns array with the objects that comply with the conditions
     */
    public getAllByKeys(primaryKeys: string[]): Observable<T[]> {

        console.log("Get all by pks");

        const path = primaryKeys.join("/");

        return this.httpClient.get<T[]>(
            `${this.apiBaseUrl}${this.resource}/${path}`
        );
    }

    /**
    * Method responsible for get all objects with some restriction or conditions
    * This is the pks array
    * 
    * @param primaryKeys Conditions or restrictions to get some response
    * @returns array with the objects that comply with the conditions
    */
    public getAllByKey(primaryKey: string): Observable<T[]> {

        console.log("Get all by one key");

        return this.httpClient.get<T[]>(
            `${this.apiBaseUrl}${this.resource}/${primaryKey}`
        );
    }

    /**
     * Method responsible for get all objects in the DB
     * 
     * @returns array with all objects in the DB with this route
     */
    public getAllObjects(): Observable<T[]> {

        console.log("Get all without keys");

        return this.httpClient.get<T[]>(
            `${this.apiBaseUrl}${this.resource}`
        );
    }

}