import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Resolve, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class LoginGuard implements CanActivate {
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        console.log('LoginGuard', sessionStorage.getItem('loggedUser'));
        return sessionStorage.getItem('loggedUser')!=null;
    }

}
