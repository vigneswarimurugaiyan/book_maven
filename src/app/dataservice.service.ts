import { Injectable } from '@angular/core';
import { Http } from '@angular/http';


@Injectable()
export class DataserviceService {

  constructor(public http:Http) { }

  //  getUser1()
  // {
  //  return this.http.get("http://192.168.1.6:8080/gtssampleproject1/getallgtsusers").map(res => res.json());
  // }
  // AddUser(user)
  // {
  //   console.log('hello');
  //   console.log(user);
  //   return this.http.post("http://192.168.1.6:8080/gtssampleproject1/savegtsuser",user).map(res => res.json());
  // }
}
