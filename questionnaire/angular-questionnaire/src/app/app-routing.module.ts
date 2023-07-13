import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegistrationPageComponent} from "./pages/registration-page/registration-page.component";
import {UserListComponent} from "./pages/user-list/user-list.component";
import {SuccessfulRegistrationComponent} from "./pages/successful-registration/successful-registration.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {ErrorPageComponent} from "./pages/error-page/error-page.component";
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {ChangePasswordPageComponent} from "./pages/change-password-page/change-password-page.component";
import {
  SuccessfulPasswordChangeComponent
} from "./pages/successful-password-change/successful-password-change.component";
import {TopicsPageComponent} from "./pages/topics-page/topics-page.component";
import {FormsPageComponent} from "./pages/forms-page/forms-page.component";

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
  },
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'change-password',
    component: ChangePasswordPageComponent
  },
  {
    path: 'successful-password-change',
    component: SuccessfulPasswordChangeComponent
  },
  {
    path: 'topics',
    component: TopicsPageComponent
  },
  {
    path: 'forms-by-topic',
    component: FormsPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
