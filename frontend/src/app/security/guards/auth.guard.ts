import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthenticationService} from '../authentication/authentication.service';
import {ToastrService} from 'ngx-toastr';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthenticationService,
              private toastr: ToastrService,
              private router: Router) {
  }

  async canActivate(): Promise<boolean> {
    if (this.authService.isLoggedIn()) {
      return true;
    } else {
      this.toastr.error('Session expired... please login again to manage products');
      this.authService.removeToken();
      await this.router.navigate(['/product']);
      return false;
    }
  }
}
