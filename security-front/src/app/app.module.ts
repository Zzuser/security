import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from "@angular/forms";

import {LoginService} from "./service/login.service";


import {AppComponent} from './app.component';
import {LoginComponent} from './page/login/login.component';
import {IndexComponent} from './page/index/index.component';
import {ModelService} from "./model/model.service";
import {httpInterceptorProviders} from "./http-interceptors";


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
        LoginService,
        ModelService,
        httpInterceptorProviders
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
