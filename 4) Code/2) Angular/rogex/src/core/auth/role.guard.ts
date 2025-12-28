import { CanActivateChildFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const roleGuard: CanActivateChildFn = (route) => {

    // CONSTANTS ---------------------------------------------------------------
    const auth = inject(AuthService);
    const router = inject(Router);

    const user = auth.user();
    const allowedRoles: string[] = route.parent?.data?.['roles'];

    // si no hay usuario → login
    if (!user) {
        return router.createUrlTree(['/log-in']);
    }

    // si no hay restricción de roles → pasa
    if (!allowedRoles || allowedRoles.length === 0) {
        return true;
    }

    // si el rol del usuario no está permitido
    if (!allowedRoles.includes(user.role)) {
        return router.createUrlTree(['/']);
    }

    return true;
};
