import { Component, OnInit } from '@angular/core';
import { DataserviceService } from '../dataservice.service';
import {HttpModule} from '@angular/http';
@Component({
  selector: 'app-fetch',
  templateUrl: './fetch.component.html',
  styleUrls: ['./fetch.component.css']
})
export class FetchComponent implements OnInit {
 users :any=[]
  constructor(public dataservice:DataserviceService) { 
    // this.dataservice.getUser1().subscribe(users =>
    // {
    //   this.users=users;
    // console.log(this.users);
    // });
   }

  ngOnInit() {
  }

}
