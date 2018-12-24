import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ModelService} from "../model/model.service";
import {Api} from "../config/api";


@Injectable({
    providedIn: 'root'
})
export class LoginService {
    constructor(
        private http: HttpClient,
        private modelService: ModelService,
    ) {
    }

    doLogin(username: string, password: string): void {
        let formData: FormData = new FormData();
        formData.append('username', username);
        formData.append('password', password);
        this.http.post(Api.do_login_url, formData)
            .subscribe(req => {
                console.log("login info", req);
                if (req["code"] == 1) {
                    this.modelService.user.login = true;
                    this.getLoginUser(username)
                }
            })
    }

    getLoginUser(username: string): void {
        this.http.get(Api.get_current_user+'/' + username)
            .subscribe(
            req => {
                console.log("user info", req);
                this.modelService.user.data = req["code"]
            }
        );
    }

}
