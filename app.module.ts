import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { IonicStorageModule } from '@ionic/storage';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import {SignupPage} from '../pages/signup/signup';
import {RegisterPage} from '../pages/register/register';
import {UserPage} from '../pages/user/user';

import {LogoutPage} from '../pages/logout/logout';
import {UserprofilePage} from '../pages/userprofile/userprofile';
import { ApiProvider } from '../providers/api/api';
import { NgIdleKeepaliveModule } from '@ng-idle/keepalive'; // this includes the core NgIdleModule but includes keepalive providers for easy wireup

import { MomentModule } from 'angular2-moment'; // optional, provides moment-style pipes for date formatting



@NgModule({
  declarations: [
    MyApp,
    HomePage,
    SignupPage,
    RegisterPage,
    UserPage,
    
    UserprofilePage,
   
    LogoutPage,
   
  ],
  imports: [
    BrowserModule,
    FormsModule,
   HttpClientModule,
   MomentModule,
   NgIdleKeepaliveModule.forRoot(),
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    SignupPage,
    RegisterPage,
    UserPage,
    UserprofilePage,
    
    LogoutPage,
   
  ],
  providers: [
    StatusBar,
    SplashScreen,
    
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    ApiProvider
    

  
    
  ]
})
export class AppModule {}
