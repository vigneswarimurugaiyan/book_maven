import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { SignupPage } from '../signup/signup';

//import { Idle,DEFAULT_INTERRUPTSOURCES } from '@ng-idle/core';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
 
  constructor(public navCtrl: NavController) {

  }
    
  nav()
  {
    this.navCtrl.push(SignupPage);
  }

}
