import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, timer } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { UserLogin } from '../model/userLogin';
import { UserWithToken } from '../model/userWithToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedUser = new BehaviorSubject<UserWithToken>(null);

  constructor(private http: HttpClient, private router: Router) {
    if (localStorage.getItem("loggedUser")) {
      let user: UserWithToken = JSON.parse(localStorage.getItem("loggedUser"));
      this.handleAuthentication(user);
    }
  }

  login(data: UserLogin) {
    return this.http.post<UserWithToken>(`${environment.baseUrl}/auth/login`, data)
      .pipe(
        tap( resData => {
          this.handleAuthentication(resData);
        })
      );
  }

  private handleAuthentication(
    resData: UserWithToken
  ) {
    const user = new UserWithToken(
      resData.accessToken, 
      resData.expiresIn, 
      resData.userId, 
      resData.userType,
      resData.passwordChanged,
      resData.numLogin,
      resData.email);
    this.loggedUser.next(user);
    localStorage.setItem('loggedUser', JSON.stringify(user));
    const source = timer(user.expiresIn);
    const subscribe = source.subscribe(val => {
      this.logout();
    });
  }

  logout() {
    this.loggedUser.next(null);
    this.router.navigate(['/login']);
    localStorage.removeItem('loggedUser');
  }
}
