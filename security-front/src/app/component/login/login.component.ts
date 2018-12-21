import {Component, OnInit} from '@angular/core';

import {LoginService} from "../../service/login.service";
import {ModelService} from "../../model/model.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(
    private loginService: LoginService,
    private modelService: ModelService,
  ) {
  }

  username: string;
  password: string;

  doLogin(): void {
    this.loginService.doLogin(this.username, this.password).then(req => {
      console.log("login info", req);
      if (req.code == 1) {
        this.modelService.user.login = true;
        this.loginService.getLoginUser(this.username).then(req=>{
          console.log("user info", req.data);
          this.modelService.user.data=req.data
        })
      }
    });
  }

  ngOnInit() {
    //set login background image
    document.body.style.backgroundImage = "url(../../assets/img/loginbg.jpg)";
  }

}
