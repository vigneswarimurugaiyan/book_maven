import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import {RouterModule,Routes} from '@angular/router';
import { FetchComponent } from './fetch/fetch.component';
import { routing }  from './app.routing';
import {HttpModule} from '@angular/http';
import { HttpClientModule } from '@angular/common/http';

import { CommonModule, NgSwitchCase } from '@angular/common';


import { DataserviceService } from './dataservice.service';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    HomeComponent,
    FetchComponent
  ],
  imports: [
    BrowserModule,
   routing,
   ReactiveFormsModule,
    HttpClientModule,
    HttpModule,
     CommonModule
  ],
  providers: [DataserviceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
