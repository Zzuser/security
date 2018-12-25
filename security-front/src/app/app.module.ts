import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from "@angular/forms";

import {LoginService} from "./service/login.service";
import {ModelService} from "./model/model.service";
import {httpInterceptorProviders} from "./http-interceptors";


import {AppComponent} from './app.component';
import {LoginComponent} from './page/login/login.component';
import {IndexComponent} from './page/index/index.component';
import { HeaderComponent } from './component/header/header.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { HomeComponent } from './page/home/home.component';
import { ProfileComponent } from './page/profile/profile.component';
import { MessagesComponent } from './page/messages/messages.component';
import { SettingsComponent } from './page/settings/settings.component';


@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        IndexComponent,
        HeaderComponent,
        NavbarComponent,
        HomeComponent,
        ProfileComponent,
        MessagesComponent,
        SettingsComponent,
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
