import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';
import 'rxjs/add/operator/map';
let apiUrl = 'http://192.168.0.107:8080/gts_sampleproject/';
@Injectable()
export class ApiProvider {
username:any;
userid:any;
dataArray:any[];
count:number=0;
myobj:any={};
data:any={};
user_token:any = {user_id:{user_id:''},user_tokenvalue:''}
  constructor(public http: HttpClient,private storage: Storage) {
    console.log('Hello ApiProvider Provider');
  }
login(credentials) {
  return new Promise((resolve, reject) => {
     this.http.post(apiUrl+'logincheck',credentials)
        .subscribe(res => {
          resolve(res);
          this.data=res;
          console.log(res);
          
        }, (err) => {
          reject(err);
        });
  });
}

setstoragevalue(user_id,user_tokenvalue,token_expireon)
{
  this.storage.clear();
this.storage.set('userid',user_id);
this.storage.set('usertokenvalue',user_tokenvalue);
this.storage.set('tokenexpireon',token_expireon);
console.log('set the values in storage');
}
getstorageid()
{
  return this.storage.get('userid').then((value) => {
    return value;
  });
}
getstoragetoken()
{
  return this.storage.get('usertokenvalue').then((value) => {
    return value;
  });  
}
deletestorage()
{
  this.storage.remove('userid');
  this.storage.remove('usertokenvalue');
  this.storage.remove('tokenexpireon');
}
setuserid(userid1)
{
  return this.storage.get('userid').then((value) => {
    return value;
  });
}
getuserdetail(userid,usertoken)
{
    this.user_token.user_id.user_id = userid;
      this.user_token.user_tokenvalue = usertoken;  
  return new Promise((resolve, reject) => {
this.http.post(apiUrl+'getuserdetail',this.user_token)
      .subscribe(res => {
        resolve(res);
      }, (err) => {
        reject(err);
      });
});
}
logout()
{
  
    
  

  return new Promise((resolve, reject) => {
    this.getstorageid().then((userid) => {
      this.userid = userid;
  this.http.get(apiUrl+'logout/'+this.userid)

      .subscribe(res => {
        
        resolve(res);
      });
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

