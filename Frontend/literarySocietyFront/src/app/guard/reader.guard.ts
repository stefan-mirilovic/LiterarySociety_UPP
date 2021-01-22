import { Injectable } from "@angular/core";
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { AuthService } from "../service/auth.service";

@Injectable()
export class ReaderGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.loggedUser.value) {
      if (this.authService.loggedUser.value.userType === 'READER' || this.authService.loggedUser.value.userType === 'BETA_READER') {
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }

    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}