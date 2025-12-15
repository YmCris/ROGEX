import { Routes } from '@angular/router';
import { StartPageComponent } from '../pages/start-page-component/start-page-component';
import { SignUpPage } from '../pages/sign-up-page/sign-up-page';
import { Shop } from '../pages/shop/shop';

export const routes: Routes = [
    {
        path: '',
        component: StartPageComponent,
    },
    {
        path: 'users/new',
        component: SignUpPage,
    },
    {
        path: 'shop',
        component: Shop,
    }
    
];
