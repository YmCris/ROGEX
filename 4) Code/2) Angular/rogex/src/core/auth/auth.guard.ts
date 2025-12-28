import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';
/**
 * Constant guard to check if the user is authenticated.
 * 
 * @param _route is the activated route
 * @param state is the router state
 * @returns true if the user is authenticated, otherwise redirects to login page
 */
export const authGuard: CanActivateFn = async (_route, state) => {

    const auth = inject(AuthService);
    const router = inject(Router);

    // Si ya hay usuario en memoria/localStorage, deja pasar (rápido)
    if (auth.isLoggedIn()) return true;

    // Si no hay, intentamos validar sesión con el backend (/me)
    try {

        await auth.refreshMe();
        return true;

    } catch {
        // No hay sesión válida
        return router.createUrlTree(['/log-in'], {
            queryParams: { returnUrl: state.url }
        });
    }
};