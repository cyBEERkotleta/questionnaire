import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegistrationPageComponent} from "./pages/registration-page/registration-page.component";
import {AboutPageComponent} from "./pages/about-page/about-page.component";
import {UserListComponent} from "./pages/user-list/user-list.component";

const routes: Routes = [
  {
    path: '',
    component: UserListComponent
  },
  {
    path: 'register',
    component: RegistrationPageComponent
  },
  {
    path: 'about',
    component: AboutPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
