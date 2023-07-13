import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent {
  private userService: UserService;
  private router: Router;

  showAllErrors = false;
  globalError: string = '';
  private successfulLogin = false;

  form = new FormGroup({
    email: new FormControl<string>('', [
      Validators.email,
      Validators.required
    ]),
    password: new FormControl<string>('', [
      Validators.required
    ])
  })

  constructor(userService: UserService,
              router: Router) {
    this.userService = userService;
    this.router = router;
  }

  isGlobalErrorSet(): boolean {
    return this.globalError != '';
  }

  submit() {
    if (this.anyErrorExists())
      return;

    this.loginUser();
  }

  private anyErrorExists(): boolean {
    this.resetGlobalError();

    if (this.isAnyErrorInFields()) {
      this.showAllErrors = true;
      return true;
    }
    return false;
  }

  private loginUser() {
    let email = this.getEmailFromField();
    let password = this.getPasswordFromField();

    this.userService.login(email, password)
      .subscribe(result => {
        console.log(result);
        if (result.success) {
          this.successfulLogin = true;
          this.navigateToMainPage();
        }
        else {
          this.globalError = result.message;
          this.successfulLogin = false;
        }
      });
  }

  private navigateToMainPage() {
    this.router.navigate(['/']);
  }

  private resetGlobalError() {
    this.globalError = '';
  }

  private getEmailFromField(): string {
    return this.form.controls.email.getRawValue();
  }

  private getPasswordFromField(): string {
    return this.form.controls.password.getRawValue();
  }

  private isAnyErrorInFields(): boolean {
    return this.doesControlHaveError(this.form.controls.email) ||
      this.doesControlHaveError(this.form.controls.password);
  }

  private doesControlHaveError(formControl: FormControl): boolean {
    return formControl.errors != null;
  }
}