import { Routes, provideRouter } from '@angular/router';
import {ProductListComponent} from './components/product-list/product-list.component';
import {LoginComponent} from './components/login/login.component';
import {AuthGuard} from './security/guards/auth.guard';
import {LoginGuard} from './security/guards/login.guard';
import {ProductAdminComponent} from './components/product-admin/product-admin.component';
import {ProductInsertComponent} from './components/product-insert/product-insert.component';
import {ProductUpdateComponent} from './components/product-update/product-update.component';

export const routes: Routes = [
  { path: 'product', component: ProductListComponent },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  { path: 'admin/product', component: ProductAdminComponent, canActivate: [AuthGuard]},
  { path: 'admin/product/insert', component: ProductInsertComponent, canActivate: [AuthGuard]},
  { path: 'admin/product/update/:id', component: ProductUpdateComponent, canActivate: [AuthGuard]},
  { path: '**', redirectTo: '/product' },
];

export const appRouting = provideRouter(routes);
