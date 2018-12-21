import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RouterModule} from "@angular/router";

import {LoginComponent} from "./component/login/login.component";
import {IndexComponent} from "./component/index/index.component";

export const Router =
  [
    {
      path: '',
      component: IndexComponent
    },
    {
      path: 'login',
      component: LoginComponent
    }
  ];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(Router)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
