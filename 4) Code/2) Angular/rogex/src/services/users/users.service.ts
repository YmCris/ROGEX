import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { User } from "../../models/users/user";
import { Observable } from "rxjs";
import { UserToUpdateRequest } from "../../models/users/update-user-request";

@Injectable({
    providedIn: "root",
})
export class UsersService {

    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public createNewUser(formData: FormData): Observable<void> {
        return this.httpClient.post<void>(
            `${this.restConstants.getApiURL()}users`,
            formData
        );
    }

    public logIn(credentials: { email: string; password: string }): Observable<User> {
        return this.httpClient.post<User>(
            `${this.restConstants.getApiURL()}users/login`,credentials
        );
    }

    public getAllUsers(): Observable<User[]> {
        return this.httpClient.get<User[]>(`${this.restConstants.getApiURL()}users`);
    }

    public getUserByEmail(email: string): Observable<User> {
        return this.httpClient.get<User>(`${this.restConstants.getApiURL()}users/${email}`);
    }

    public updateUser(email: string, userToUpdate: UserToUpdateRequest): Observable<User> {
        return this.httpClient.put<User>(`${this.restConstants.getApiURL()}users/${email}`, userToUpdate);
    }

    public deleteUser(email: string): Observable<void> {
        return this.httpClient.delete<void>(`${this.restConstants.getApiURL()}users/${email}`);
    }
}