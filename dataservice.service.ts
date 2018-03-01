import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class DataserviceService {

  constructor(public http:Http) { }

  getUser()
  {
   return this.http.get("http://localhost:8081/Spring/getuser").map(res => res.json());
  }
  AddUser(user)
  {
    return this.http.post("http://localhost:8081/Spring/user",user).map(res => res.json());
  }
  
  
}
