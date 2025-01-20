import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthenticationService} from '../authentication/authentication.service';

@Injectable({providedIn: 'root'})
export class LoginGuard implements CanActivate {

  constructor(private authService: AuthenticationService, private router: Router) {
  }

  async canActivate(): Promise<boolean> {
    if (this.authService.isLoggedIn()) {
      await this.router.navigate([`/admin/product`]);
      return false;
    } else {
      return true;
    }
  }
}
