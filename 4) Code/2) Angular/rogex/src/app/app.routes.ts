import { Routes } from '@angular/router';
import { authGuard } from '../core/auth/auth.guard';
import { roleGuard } from '../core/auth/role.guard';

export const routes: Routes = [

    // Layouts with Lazy Loading of components and authentication layouts
    {
        path: '',
        loadComponent: () =>
            import('../layouts/start-layout-component/start-layout-component')
                .then(m => m.StartLayoutComponent),
        children: [
            {
                path: '', loadComponent: () => import('../pages/start-page-component/start-page-component')
                    .then(m => m.StartPageComponent)
            },
            {
                path: 'sign-up', loadComponent: () => import('../pages/sign-up-page/sign-up-page')
                    .then(m => m.SignUpPage)
            },
            {
                path: 'log-in', loadComponent: () => import('../pages/log-in-page/log-in-page')
                    .then(m => m.LogInPage)
            }
        ],
    },

    {
        path: 'gamer',
        loadComponent: () =>
            import('../layouts/user-layout-component/user-layout-component')
                .then(m => m.UserLayoutComponent),
        canActivate: [authGuard],
        canActivateChild: [roleGuard],
        data: { roles: ['USER'] },
        children: [
            {
                path: '', loadComponent: () => import('../pages/user-page/user-page')
                    .then(m => m.UserPage)
            },
            {
                path: 'shop', loadComponent: () => import('../pages/shop/shop')
                    .then(m => m.Shop)
            }
        ]
    },


    {
        path: 'employee',
        loadComponent: () =>
            import('../layouts/enterprise-layout-component/enterprise-layout-component')
                .then(m => m.EnterpriseLayoutComponent),
        canActivate: [authGuard],
        canActivateChild: [roleGuard],
        data: { roles: ['ENTERPRISE'] },
        children: [
            {
                path: '', loadComponent: () => import('../pages/enterprise-page/enterprise-page')
                    .then(m => m.EnterprisePage)
            },
            {
                path: 'shop', loadComponent: () => import('../pages/shop/shop')
                    .then(m => m.Shop)
            }
        ]
    },

    {
        path: 'admin',
        loadComponent: () =>
            import('../layouts/admin-layout-component/admin-layout-component')
                .then(m => m.AdminLayoutComponent),
        canActivate: [authGuard],
        canActivateChild: [roleGuard],
        data: { roles: ['ADMIN'] },
        children: [
            {
                path: '', loadComponent: () => import('../pages/admin-page/admin-page')
                    .then(m => m.AdminPage)
            },
            {
                path: 'shop', loadComponent: () => import('../pages/shop/shop')
                    .then(m => m.Shop)
            }
        ]

    },

    { path: '**', redirectTo: '' }

];
