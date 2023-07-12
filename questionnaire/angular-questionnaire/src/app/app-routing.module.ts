import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegistrationPageComponent} from "./pages/registration-page/registration-page.component";
import {UserListComponent} from "./pages/user-list/user-list.component";
import {SuccessfulRegistrationComponent} from "./pages/successful-registration/successful-registration.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {ErrorPageComponent} from "./pages/error-page/error-page.component";

const routes: Routes = [
  {
    path: 'user-list',
    component: UserListComponent
  },
  {
    path: 'register',
    component: RegistrationPageComponent
  },
  {
    path: 'successful-registration',
    component: SuccessfulRegistrationComponent
  },
  {
    path: '',
    component: MainPageComponent
  },
  {
    path: 'error-page',
    component: ErrorPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
