import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Resolve, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class LoginGuard implements CanActivate {

    constructor(private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        let isLogin = sessionStorage.getItem('loggedUser')!=null;
        if (isLogin) {
            return true;
        } else {
            this.router.navigateByUrl('/login');
            return false;
        }
    }

}
