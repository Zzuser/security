import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Api} from "../config/api";

@Injectable({
    providedIn: 'root'
})
export class IndexService {
    constructor(
        private http: HttpClient,
    ) {
    }

    getHome() {
        return this.http.get(Api.get_path_user_test);
    }

    getMessage() {
        return this.http.get(Api.get_path_admin);
    }
}
