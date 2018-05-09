import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { FetchComponent } from './fetch/fetch.component';

const appRoutes: Routes = [
  
     {path:'home',component:HomeComponent},
      {path:'register',component:RegisterComponent},
      {path:'fetch',component:FetchComponent}
 
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);


