import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RouterModule, Routes} from "@angular/router";

import {LoginComponent} from "./page/login/login.component";
import {IndexComponent} from "./page/index/index.component";
import {HomeComponent} from "./page/home/home.component";
import {ProfileComponent} from "./page/profile/profile.component";
import {MessagesComponent} from "./page/messages/messages.component";
import {SettingsComponent} from "./page/settings/settings.component";

const appRoutes: Routes =
    [
        {
            path: '',
            component: IndexComponent,
            children: [
    { path: 'home',
        component: HomeComponent
    },
    { path: 'profile',
        component: ProfileComponent
    },
    { path: 'messages',
        component: MessagesComponent
    },
    {
        path: 'settings',
        component: SettingsComponent
    },
                ]
        },
        {
            path: 'login',
            component: LoginComponent
        }
    ];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule],
    declarations: []
})
export class AppRoutingModule {
}
