import { Injectable } from '@angular/core';
import {User} from "./user.model";

@Injectable({
  providedIn: 'root'
})
export class ModelService {
  private _user:User=new User();

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }

  constructor() { }
}
