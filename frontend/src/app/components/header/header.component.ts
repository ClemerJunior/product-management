import { Component } from '@angular/core';
import {AuthenticationService} from '../../security/authentication/authentication.service';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-header',
  imports: [CommonModule, MatButton],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  standalone:true
})
export class HeaderComponent {

  constructor(private authService: AuthenticationService,
              private router: Router) {}

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  get username(): string|null {
    return this.authService.getUsername();
  }

  login(): void {
    this.router.navigateByUrl('/login');
  }

  logout(): void {
    this.authService.removeToken();
    this.router.navigate(['/product']);
  }
}
