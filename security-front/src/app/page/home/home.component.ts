import {Component, OnInit} from '@angular/core';
import {IndexService} from "../../service/index.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    message: string;

    constructor(
        private homeService: IndexService
    ) {
    }

    ngOnInit() {
        this.homeService.getHome().subscribe(res => {
            this.message = res['msg']
        })
    }

}
