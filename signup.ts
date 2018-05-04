import { Component } from '@angular/core';
import { IonicPage, NavController} from 'ionic-angular';
import { RegisterPage} from '../register/register';
import { Platform } from 'ionic-angular';
import { ApiProvider } from './../../providers/api/api';
import 'rxjs/add/operator/toPromise';
import { ViewChild } from '@angular/core';
import { Navbar } from 'ionic-angular';
import { UserprofilePage } from '../userprofile/userprofile';
import { App, AlertController } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-signup',
  templateUrl: 'signup.html',
})
export class SignupPage {
  loginData = { user_name:'', user_pwd:'' };
 b: boolean = true;
 flag=1;
 flagcount:number=1;
 data:any={};
 public unregisterBackButtonAction: any;
 @ViewChild(Navbar) navBar: Navbar;
  constructor(public navCtrl: NavController,public apiProvider: ApiProvider,public platform: Platform,public  app: App, public alertCtrl: AlertController) {
    console.log("i am in signup constructor");
    
  }

   ionViewDidEnter() {
    
    this.navBar.backButtonClick = (e:UIEvent)=>{
     this.navCtrl.push(SignupPage);
    }
  }



  
  doLogin()
  {
  
      console.log('inside the signup page');
      this.data=this.apiProvider.login(this.loginData).then((result) => {
       console.log('inside login page');
       this.data=result;
       if(this.data.user_tokenid!=0)
       {
        console.log(result);
        console.log(this.data);
        this.apiProvider.setstoragevalue(this.data.user_id.user_id,this.data.user_tokenvalue,this.data.token_expireon);
        console.log(this.data.user_tokenid);
        console.log(this.data.user_id.user_name);
        this.navCtrl.push(UserprofilePage);
       }
       else
       {
       
       this.flag=0;
       this.navCtrl.push(SignupPage);
       }
      }, (err) => {
        console.log(err);
      });
    }
}
