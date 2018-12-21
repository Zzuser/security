import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from "@angular/forms";

import {LoginService} from "./service/login.service";
import {InterceptorService} from "./service/interceptor.service";


import {AppComponent} from './app.component';
import {LoginComponent} from './component/login/login.component';
import {IndexComponent} from './component/index/index.component';
import {ModelService} from "./model/model.service";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    InterceptorService,
    LoginService,
    ModelService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
