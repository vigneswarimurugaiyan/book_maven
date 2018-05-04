import { Component} from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ApiProvider } from './../../providers/api/api';
import { SignupPage } from '../signup/signup';
import {Idle, DEFAULT_INTERRUPTSOURCES} from '@ng-idle/core';
import {Keepalive} from '@ng-idle/keepalive';
import { Platform } from 'ionic-angular';
import * as moment from 'moment';
@IonicPage()
@Component({
  selector: 'page-userprofile',
  templateUrl: 'userprofile.html',
})
export class UserprofilePage {
 
  userid:number;
  usertoken:string;
  data:any=[];
  data1:any;
dataArray:any[];
idleState = 'Not started.';
timedOut = false;
model_flag:number=0;
starttime:any;
endtime:any;
idleflag:number=1;
idlecount:number=1;
flag_logout:boolean=false;
booleanvalue:any;
public unregisterBackButtonAction: any;
  constructor(public navCtrl: NavController,public platform: Platform,public navParams: NavParams,public apiProvider: ApiProvider,private storage: Storage,private idle: Idle, private keepalive: Keepalive) {
          idle.setIdle(10);
          idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);
          idle.onIdleEnd.subscribe(() => {
          idle.onTimeout.subscribe(() => {
          this.idleState = 'Timed out!';
          this.timedOut = true;
             }); 
          });
          idle.onIdleStart.subscribe(() => {this.idleState = 'You\'ve gone idle!';
          if(this.idleflag==1 && this.idlecount==1)
          {
         
          this.model_flag=1;
          this.starttime=moment(); 
          this.idlecount=0;
          }
         });
         this.idle.watch();
         this.idleState = 'Started.';
         this.timedOut = false;
         this.model_flag=0;
}

  reset() {
  
     this.endtime = moment();
    
    
this.starttime.add(1,'minutes'); 
this.booleanvalue=moment(this.starttime).isAfter(this.endtime);
if(this.booleanvalue)
{
this.idle.watch();
   this.idleState = 'Started.';
   this.timedOut = false;
     this.model_flag=0;
     this.idlecount=1;
}
else
{
  this.logout();
}
  
}
getdetail()
  {
    if(this.model_flag!=1)
    {
    this.apiProvider.getstorageid().then(userid =>
      {
  this.userid = userid;
     
      this.apiProvider.getstoragetoken().then(usertoken =>
        {
          this.usertoken = usertoken;
       

    this.apiProvider.getuserdetail(this.userid,this.usertoken).then(data => {
      console.log('inside userprofile.ts');
         console.log(data);
      
         this.data=Array.of(data);
         console.log(this.data);
       }, (err) => {
         console.log(err);
      });
    }); 
  });
}
else{

} 
  }
getUserid() {
  if(this.model_flag!=1)
  {
   this.apiProvider.getstorageid().then((userid) => {
       this.userid = userid;
     
        console.log('my result :'+this.userid);
      
     }); 
    }
    
   } 
   logout()
   {
    this.flag_logout=true;
  this.apiProvider.logout().then(data => {
      console.log('inside logout method');
          console.log(data);
          this.data1=data;
        if(this.data1==true)
        {
          this.apiProvider.deletestorage();
          this.navCtrl.push(SignupPage);

      }
      else{
        console.log('error in delete action');
      }
        });
}
}




