import { Injectable } from "@angular/core";
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { AuthService } from "../service/auth.service";

@Injectable()
export class ComitteeLeaderGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.loggedUser.value) {
      if (this.authService.loggedUser.value.userType === 'COMITTEE_LEADER') {
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