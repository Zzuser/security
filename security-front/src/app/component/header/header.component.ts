import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../auth/Auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
      private loginService:AuthService
  ) { }

  doLogout(){
    this.loginService.doLogout()
  }

  ngOnInit() {
  }

}
