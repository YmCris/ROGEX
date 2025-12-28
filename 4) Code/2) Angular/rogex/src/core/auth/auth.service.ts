import { Injectable, signal, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { RestConstants } from '../../shared/restapi/rest-constants';
import { UserLog } from '../../models/log-in/user-log';

type AuthResponse = { user: UserLog };

@Injectable({ providedIn: 'root' })
export class AuthService {

    // VARIABLES ---------------------------------------------------------------
    private readonly storageKey = 'auth_user';
    private readonly restConstant = new RestConstants();

    private _user = signal<UserLog | null>(this.loadUserFromStorage());
    user = computed(() => this._user());

    isLoggedIn = computed(() => !!this._user());

    // CONSTRUCTOR & METHODS ---------------------------------------------------
    constructor(private http: HttpClient) { }

    private loadUserFromStorage(): UserLog | null {
        const raw = localStorage.getItem(this.storageKey);
        if (!raw) return null;
        try {
            return JSON.parse(raw) as UserLog;
        } catch {
            return null;
        }
    }

    private saveUser(user: UserLog | null) {
        this._user.set(user);
        if (user) localStorage.setItem(this.storageKey, JSON.stringify(user));
        else localStorage.removeItem(this.storageKey);
    }

    async login(email: string, password: string): Promise<UserLog> {
        const user = await firstValueFrom(
            this.http.post<UserLog>(
                `${this.restConstant.getApiURL()}auth/login`,
                { email, password },
            )
        );
        this.saveUser(user);
        return user;
    }

    async logout(): Promise<void> {
        await firstValueFrom(
            this.http.post(`${this.restConstant.getApiURL()}auth/logout`, {})
        );
        this.saveUser(null);
    }

    // Al recargar la página. re-validemos la sesión con backend
    async refreshMe(): Promise<UserLog> {
        const user = await firstValueFrom(
            this.http.get<UserLog>(`${this.restConstant.getApiURL()}auth/me`)
        );
        this.saveUser(user);
        return user;
    }

    clearLocalUser() {
        this.saveUser(null);
    }
}
