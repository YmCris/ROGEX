import { Routes } from '@angular/router';
import { StartPageComponent } from '../pages/start-page-component/start-page-component';
import { SignUpPage } from '../pages/sign-up-page/sign-up-page';
import { LogInPage } from '../pages/log-in-page/log-in-page';
import { Shop } from '../pages/shop/shop';

export const routes: Routes = [
    {
        path: '',
        component: StartPageComponent,
    },
    {
        path: 'sign-up',
        component: SignUpPage,
    },
    {
        path: 'log-in',
        component: LogInPage,
    },
    {
        path: 'shop',
        component: Shop,
    }
    
];
