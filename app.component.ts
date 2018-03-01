import { DataserviceService } from './dataservice.service';
import { Component } from '@angular/core';
import {HttpModule} from '@angular/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  users :any[];
  user ={
    id:"",
    title:"",
  firstname:"",
    lastname:"",
    dateofbirth:"",
    email:""
    
  }
  tarray=["Mr","Ms","Mrs"];
  constructor(public dataservice:DataserviceService)
  {
    this.dataservice.getUser().subscribe(users =>
    {
      this.users=users;
    });
  }
  onsubmit()
  {
 this.dataservice.AddUser(this.user).subscribe(user=>
 {
  // this.users = (this.user);
 })
    
  }
}
