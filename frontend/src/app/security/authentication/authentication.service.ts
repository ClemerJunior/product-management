import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable} from 'rxjs';
import { LoginModel} from '../../models/login.model';
import {JwtHelperService} from '@auth0/angular-jwt';
import {AuthenticationModel} from '../../models/authentication.model';

const KEY:string = 'token';
const USER:string = 'user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private apiUrl = 'http://localhost:8080/authentication';

  private helper = new JwtHelperService();

  constructor(private http: HttpClient) {}

  public authenticate(login: LoginModel): Observable<AuthenticationModel> {
    return this.http.post<AuthenticationModel>(this.apiUrl, login);
  }

  hasToken(): boolean {
    return !!this.getToken();
  }

  setToken(token: string): void {
    localStorage.setItem(KEY, token);
  }

  setUser(username: string): void {
    localStorage.setItem(USER, username);
  }

  getToken(): string|null {
    return localStorage.getItem(KEY);
  }

  removeToken(): void {
    localStorage.removeItem(KEY);
    localStorage.removeItem(USER)
  }

  getUsername(): string|null {
    return localStorage.getItem("user")
  }

  isLoggedIn(): boolean {
    return this.hasToken() && !this.helper.isTokenExpired(this.getToken());
  }
}




