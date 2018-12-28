import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Api} from "../config/api";
import {Router} from "@angular/router";


@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(
        private http: HttpClient,
        private router: Router
    ) {
    }

    doLogin(username: string, password: string): void {
        let formData: FormData = new FormData();
        formData.append('username', username);
        formData.append('password', password);
        this.http.post(Api.do_login_url, formData)
            .subscribe(res => {
                console.log("login info", res);
                if (res["code"] == 1) {
                    this.getLoginUser()
                }
            })
    }

    doLogout(): void {
        this.http.get(Api.do_logout_url)
            .subscribe(res => {
                document.body.style.backgroundImage = "url(../../assets/img/loginbg.jpg)";
                sessionStorage.clear()
            })
    }

    getLoginUser(): void {
        this.http.get(Api.get_current_user)
            .subscribe(
                res => {
                    console.log("user info", res);
                    if (res["code"] == 1) {
                        sessionStorage.setItem('loggedUser', res['data']);
                        document.body.style.backgroundImage = "";
                        this.router.navigateByUrl('')
                    }
                }
            );
    }

}
