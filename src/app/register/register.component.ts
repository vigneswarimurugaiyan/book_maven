import { Component, OnInit } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { DataserviceService } from '../dataservice.service';
import {HttpModule} from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-register',
 template:`
<form *ngIf="myform" (ngSubmit)="onSubmit(myform.value)" [formGroup]="myform" novalidate>

<div class="form-group">
<h4>Customer Registration Form:</h4> 
     
<div *ngFor="let prop of objectProps">
        <label [attr.for]="prop">{{prop.label}}</label>

        <div [ngSwitch]="prop.type">
          <input [(ngModel)]="prop.model" *ngSwitchCase="'text'" style="width:35%;"
            [formControlName]="prop.key"
            [id]="prop.key" [type]="prop.type">

            <input [(ngModel)]="prop.model" *ngSwitchCase="'number'"
            [formControlName]="prop.key"
            [id]="prop.key" [type]="prop.type">


  <input *ngSwitchCase="'date'" class="datepicker" style="width:35%;"
            [formControlName]="prop.key" class="form-control" [(ngModel)]="prop.model"
            [id]="prop.key" [type]="prop.type">
<input *ngSwitchCase="'email'" class="form-control" [(ngModel)]="prop.model" style="width:35%;"
            [formControlName]="prop.key"
            [id]="prop.key" [type]="prop.type">


             
            <select *ngSwitchCase="'select'" [formControlName]="prop.key" [(ngModel)]="prop.model" class="form-control" style="width:35%;">
              <option *ngFor="let option of prop.options" [value]="option.value">
                {{ option.label }}
              </option>
            </select>
        

        </div>


        <div style="display:inline-flex;" class="error" *ngIf="myform.get(prop.key).invalid && (myform.get(prop.key).dirty || myform.get(prop.key).touched)">
        <div [hidden]="!myform.get(prop.key).errors.required">
      {{prop.label}} is required
        
        </div>
        <div *ngIf="myform.get(prop.key).errors?.minlength">
     Name must be at least 3 characters long.
</div>
<div *ngIf="myform.get(prop.key).errors?.maxlength"> 
     Name can be max 25 characters long.
</div> 
<div *ngIf="myform.get(prop.key).errors?.pattern"> 
Email not valid.
</div>
       

        </div>
       
         </div>
    
      <p>
        


        <button type="reset" class="btn btn-default">Clear</button>
                <button [disabled]="!myform.valid" type="submit" class="btn btn-primary">Submit</button>
      </p>
    
      </div>
    
    </form>
  `,
  styles: [
    `
    .error { color: red; }
    `
  ]  

})
export class RegisterComponent implements OnInit {
   users :any[];
 user ={
   
    title:"",
  firstname:"",
    lastname:"",
  
    email:"",
      dateofbirth:"",
    
  }
  
   title:string="";
  firstname:string="";
    lastname:string="";
    dateofbirth:string="";
    email:string="";
    emailPattern = "^[A-Za-z0-9._%+-]+@(?!testdomain.com)[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$";
  arrdata:{};
  objectProps;

   myform: FormGroup;

  
  
  

  constructor(public dataservice:DataserviceService,private httpService: HttpClient)
  {
    
  }
  ngOnInit() {


    
    
       this.httpService.get('./assets/input.json').subscribe(
      data => {
        this.arrdata = data;
        console.log(data);
        console.log(typeof data);
        console.log("my arrdata");
        console.log(this.arrdata);
        console.log("keys");
        console.log(Object.keys(this.arrdata));
         this.objectProps =
      Object.keys(this.arrdata)
        .map(prop1 => {
          return Object.assign({}, { key: prop1} , this.arrdata[prop1]);
        });
        console.log(typeof this.objectProps);
        console.log(this.objectProps);
        console.log('hello, before objectprops');
              const formGroup = {};
    for(let prop1 of Object.keys(this.arrdata)) {
      console.log("this.arrdata[prop1]");
       console.log(this.arrdata[prop1]);
       

      formGroup[prop1] = new FormControl('', this.mapValidators(this.arrdata[prop1].validation));
      console.log("refer me");
       console.log(formGroup[prop1]);
    }
    this.myform = new FormGroup(formGroup);
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
 
  }
  
  
    private mapValidators(validators) {
    const formValidators = [];

    if(validators) {
      for(const validation of Object.keys(validators)) {
        if(validation === 'required') {
          formValidators.push(Validators.required);
        } else if(validation === 'minLength') {
          formValidators.push(Validators.minLength(validators[validation]));
        }
        else if(validation === 'maxLength') {
          formValidators.push(Validators.maxLength(validators[validation]));
        }
        else if(validation === 'pattern') {
          formValidators.push(Validators.pattern(validators[validation]));
        }
     

      }
    }

    return formValidators;
  }


  
  onSubmit(myform)
  {
     var a=Object.keys(this.user);
   console.log(a);
    console.log(typeof a);
   
    var outputobj={};
    var l:number=0;
  
    for (let key in a) {
    let ab: any;
      ab = a[key];
      outputobj[ab] = this.myform.value[l];
        l++;
    console.log(ab);
}
    console.log(outputobj);
   console.log('myform');
   
    console.log(this.myform.value[1]);
 //this.dataservice.AddUser(outputobj).subscribe(user => {

// });
  }
}
