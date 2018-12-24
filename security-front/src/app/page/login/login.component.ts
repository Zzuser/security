import {Component, OnInit} from '@angular/core';

import {LoginService} from "../../service/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(
    private loginService: LoginService,
  ) {
  }

  username: string;
  password: string;

  doLogin(): void {
    this.loginService.doLogin(this.username, this.password)
  }

  ngOnInit() {
    //set login background image
    document.body.style.backgroundImage = "url(../../assets/img/loginbg.jpg)";
  }

}
