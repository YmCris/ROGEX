import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Observable } from "rxjs";
import { UpdateCategoryRequest } from "../../models/categories/UpdateCategoryRequest";
import { Category } from "../../models/categories/Category";

@Injectable({
    providedIn: "root",
})
export class CategoryService {

    // VARIABLES ---------------------------------------------------------------
    restConstants = new RestConstants();

    // CONSTRUCTOR -------------------------------------------------------------
    constructor(private httpClient: HttpClient) { }

    // METHODS -----------------------------------------------------------------
    public createCategory(request: { name: string }): Observable<void> {
        return this.httpClient.post<void>(
            `${this.restConstants.getApiURL()}categories`,
            request
        );
    }


    public updateCategory(name: string, update: UpdateCategoryRequest): Observable<Category> {
        return this.httpClient.put<Category>(`${this.restConstants.getApiURL()}categories/${name}`, update);
    }

    public deleteCategory(name: string): Observable<void> {
        return this.httpClient.delete<void>(`${this.restConstants.getApiURL()}categories/${name}`);
    }

    public getCategory(name: string): Observable<Category> {
        return this.httpClient.get<Category>(`${this.restConstants.getApiURL()}categories/${name}`);
    }

    public getAllCategories(): Observable<Category[]> {
        return this.httpClient.get<Category[]>(`${this.restConstants.getApiURL()}categories`);
    }

}