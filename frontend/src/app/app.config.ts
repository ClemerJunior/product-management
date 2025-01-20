import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import {provideToastr} from 'ngx-toastr';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {AuthInterceptor} from './security/authentication/request.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    provideToastr(),
    provideAnimationsAsync(),
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}]
};
