import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) {
  }


  doLogin(username: string, password: string): Promise<any> {
    let formData: FormData = new FormData();
    formData.append('username', username);
    formData.append('password', password);
    return this.http.post('http://localhost:8080/login',
      formData
    )
      .toPromise()
      .catch(LoginService.handleError);
  }
  getLoginUser(username: string): Promise<any> {
    return this.http.get('http://localhost:8080/user/'+username)
      .toPromise()
      .catch(LoginService.handleError);
  }


  static handleError(): void {
    alert('网络出现问题');
  }
}
