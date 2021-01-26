import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable, Subscription, timer } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { UserLogin } from '../model/userLogin';
import { UserWithToken } from '../model/userWithToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  subscribe: Subscription;
  loggedUser = new BehaviorSubject<UserWithToken>(null);

  constructor(private http: HttpClient, private router: Router, private toastr: ToastrService) {
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
    this.subscribe = source.subscribe(val => {
      this.loggedUser.next(null);
      localStorage.removeItem('loggedUser');
      this.toastr.info("Login expired, please login again", "", {
        timeOut: 0,
        extendedTimeOut: 0
      })
      this.subscribe.unsubscribe();
      this.router.navigate(["/login"]);
    });
  }

  logout() {
    this.loggedUser.next(null);
    this.router.navigate(['/login']);
    localStorage.removeItem('loggedUser');
    this.subscribe.unsubscribe();
  }
}
