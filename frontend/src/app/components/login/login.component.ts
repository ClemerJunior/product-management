import {Component} from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../security/authentication/authentication.service';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import {LoginModel} from '../../models/login.model';
import {AuthenticationModel} from '../../models/authentication.model';


@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  standalone: true
})
export class LoginComponent {

  form = new FormGroup({
    username: new FormControl("", [Validators.required]),
    password: new FormControl("", [Validators.required])
  })

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) {}


  login(): void {
    this.authService.removeToken();
    if (this.form.valid) {
      const login: LoginModel = {
        username: this.form.value.username?? '',
        password: this.form.value.password?? ''
      };

      this.authService.authenticate(login).subscribe(
        (token: AuthenticationModel) => {
          this.authService.setToken(token.token);
          this.authService.setUser(token.user)
          this.router.navigate([`/product`]);
        }, (err) => {
          this.toastr.error('Incorrect credentials');
          this.router.navigate([`/product`]);
        }
      );
    } else {
      this.toastr.error('Fill all the fields');
    }
  }
}
