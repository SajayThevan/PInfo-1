import { AddReceipeComponent } from './add-receipe/add-receipe.component';
import { MyFridgeComponent } from './my-fridge/my-fridge.component';
import { KeycloakAuthGuard } from './services/keycloak/keycloak-auth-gard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
  {
    path: 'my-fridge',
    // loadChildren: () => import('./my-fridge/my-fridge.module').then(m => m.MyFridgeModule) ,
    component: MyFridgeComponent,
    canActivate: [KeycloakAuthGuard],
    data: { roles: ['User'] }
  },
  {
    path: 'add-receipe',
    // loadChildren: () => import('./my-fridge/my-fridge.module').then(m => m.MyFridgeModule) ,
    component: AddReceipeComponent,
    canActivate: [KeycloakAuthGuard],
    data: { roles: ['User'] }
  }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [KeycloakAuthGuard]
})
export class AppRoutingModule { }

