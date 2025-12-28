import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { RestConstants } from '../../shared/restapi/rest-constants';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

    // VARIABLES ---------------------------------------------------------------
    const auth = inject(AuthService);
    const router = inject(Router);
    const restConstants = new RestConstants();
    const API_URL = restConstants.getApiURL();

    // Solo aplica a requests hacia mi API (localhost:8080/xxxx/api/v1)
    const isApiRequest = req.url.startsWith(API_URL);

    const reqWithCredentials = isApiRequest
        ? req.clone({ withCredentials: true })
        : req;

    // METHODS -----------------------------------------------------------------
    return next(reqWithCredentials).pipe(
        catchError((err: unknown) => {

            if (router.url.startsWith('/log-in')) return throwError(() => err);

            if (err instanceof HttpErrorResponse && err.status === 401) {
                // sesión inválida/expirada → limpiamos user local
                auth.clearLocalUser();

                // evita loops si ya estás en login
                if (router.url !== '/log-in') {
                    router.navigateByUrl('/log-in');
                }
            }
            return throwError(() => err);
        })
    );
};
