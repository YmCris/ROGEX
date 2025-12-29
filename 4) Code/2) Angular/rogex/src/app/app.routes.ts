import { Routes } from '@angular/router';
import { authGuard } from '../core/auth/auth.guard';
import { roleGuard } from '../core/auth/role.guard';

export const routes: Routes = [

    // Layouts with Lazy Loading of components and authentication layouts
    {// Start Pages
        path: '',
        loadComponent: () =>
            import('../layouts/start-layout-component/start-layout-component')
                .then(m => m.StartLayoutComponent),
        children: [
            {
                path: '', loadComponent: () => import('../pages/system/start-page-component/start-page-component')
                    .then(m => m.StartPageComponent)
            },
            {
                path: 'sign-up', loadComponent: () => import('../pages/system/sign-up-page/sign-up-page')
                    .then(m => m.SignUpPage)
            },
            {
                path: 'log-in', loadComponent: () => import('../pages/system/log-in-page/log-in-page')
                    .then(m => m.LogInPage)
            }
        ],
    },

    {// Gamer pages
        path: 'gamer',
        loadComponent: () =>
            import('../layouts/user-layout-component/user-layout-component')
                .then(m => m.UserLayoutComponent),
        canActivate: [authGuard],
        canActivateChild: [roleGuard],
        data: { roles: ['USER'] },
        children: [
            {
                path: '', loadComponent: () => import('../pages/users/user-page/user-page')
                    .then(m => m.UserPage)
            },
            {
                path: 'shop', loadComponent: () => import('../pages/users/shop/shop')
                    .then(m => m.Shop)
            }
        ]
    },


    {// Enterprise pages
        path: 'employee',
        loadComponent: () =>
            import('../layouts/enterprise-layout-component/enterprise-layout-component')
                .then(m => m.EnterpriseLayoutComponent),
        canActivate: [authGuard],
        canActivateChild: [roleGuard],
        data: { roles: ['ENTERPRISE'] },
        children: [
            {
                path: '', loadComponent: () => import('../pages/enterprises/enterprise-page/enterprise-page')
                    .then(m => m.EnterprisePage)
            },
            {
                path: 'employees', loadComponent: () => import('../pages/enterprises/employees-page/employees-page')
                    .then(m => m.EmployeesPage)
            },
            {
                path: 'videogames', loadComponent: () => import('../pages/enterprises/videogames-page/videogames-page')
                    .then(m => m.VideogamesPage)
            },
            {
                path: 'configurations', loadComponent: () => import('../pages/enterprises/configurations-page/configurations-page')
                    .then(m => m.ConfigurationsPage)
            },
            {
                path: 'log-out', loadComponent: () => import('../pages/system/log-in-page/log-in-page')
                    .then(m => m.LogInPage)
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
                path: '', loadComponent: () => import('../pages/admins/admin-page/admin-page')
                    .then(m => m.AdminPage)
            },
            {
                path: 'commission/global', loadComponent: () => import('../pages/admins/global-configurations-page/global-configurations-page')
                    .then(m => m.GlobalConfigurationsPage)
            },
            {
                path: 'categories', loadComponent: () => import('../pages/admins/categories-page/categories-page')
                    .then(m => m.CategoriesPage)
            },
            {
                path: 'video-games/categories', loadComponent: () => import('../pages/admins/videogames-categories-page/videogames-categories-page')
                    .then(m => m.VideogamesCategoriesPage)
            },
            {
                path: 'main-banner', loadComponent: () => import('../pages/admins/main-banner-page/main-banner-page')
                    .then(m => m.MainBannerPage)
            },
            {
                path: 'enterprises', loadComponent: () => import('../pages/admins/enterprises-page/enterprises-page')
                    .then(m => m.EnterprisesPage)
            },
            {
                path: 'log-out', loadComponent: () => import('../pages/system/log-in-page/log-in-page')
                    .then(m => m.LogInPage)
            }

        ]

    },

    { path: '**', redirectTo: '' }

];
