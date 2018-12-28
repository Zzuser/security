import {Component, OnInit} from '@angular/core';
import {IndexService} from "../../service/index.service";

@Component({
    selector: 'app-messages',
    templateUrl: './messages.component.html',
    styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {
    msg: string;

    constructor(
        private indexService: IndexService
    ) {
    }

    ngOnInit() {
        this.indexService.getMessage().subscribe(res => {
            this.msg = res['msg']
        })
    }

}
