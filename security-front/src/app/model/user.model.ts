export class User {
  private _data: object;
  private _login: boolean = false;


  constructor() {
    //设置全局的控制导航是否显示
  }


  get data(): object {
    return this._data;
  }

  set data(value: object) {
    this._data = value;
  }

  get login(): boolean {
    return this._login;
  }

  set login(value: boolean) {
    this._login = value;
  }
}
