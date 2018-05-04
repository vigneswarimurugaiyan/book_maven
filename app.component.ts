import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { App, AlertController } from 'ionic-angular';
import { ApiProvider } from '../providers/api/api';

import { HomePage } from '../pages/home/home';
import { SignupPage } from '../pages/signup/signup';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage= SignupPage;
  data1:any;
 
 
  constructor(public apiProvider: ApiProvider,public platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, public  app: App, public alertCtrl: AlertController) {
      platform.ready().then(() => {
          statusBar.styleDefault();
          splashScreen.hide();
          let mynav=app.getActiveNavs()[0];
        
        console.log(mynav.getActive());
          platform.registerBackButtonAction(() => {

              //let nav = app.getActiveNavs()[0];
              //console.log(nav);
              //let activeView = nav.getActive();                

              //if(activeView.name === "SignupPage") {

                                   const alert = this.alertCtrl.create({
                          title: 'App termination',
                          message: 'Do you want to close the app?',
                          buttons: [{
                              text: 'Cancel',
                              role: 'Cancel',
                              handler: () => {
                                  console.log('Application exit prevented!');
                              }
                          },{
                              text: 'Close App',
                              handler: () => {
                                this.logout();
                                  this.platform.exitApp(); // Close this application
                              }
                          }]
                      });
                      alert.present();
                  
                    //}
          });
      });
  }
  logout()
  {
  
 this.apiProvider.logout().then(data => {
     console.log('inside logout method');
         console.log(data);
         this.data1=data;
       if(this.data1==true)
       {
         this.apiProvider.deletestorage();
       

     }
     else{
       console.log('error in delete action');
     }
       });
}
  
}

