import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-restore-password',
  templateUrl: './restore-password.component.html',
  styleUrls: ['./restore-password.component.css']
})
export class RestorePasswordComponent {
  private userService: UserService;
  private router: Router;
  private location: Location;

  showAllErrors = false;
  globalError: string = '';

  private subscription: Subscription;

  form = new FormGroup({
    newPassword: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(30)
    ]),
    confirmNewPassword: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(30)
    ])
  })

  constructor(userService: UserService,
              router: Router,
              location: Location) {
    this.userService = userService;
    this.router = router;
    this.location = location;
  }

  ngOnDestroy() {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  isGlobalErrorSet(): boolean {
    return this.globalError != '';
  }

  submit() {
    if (this.anyErrorExists())
      return;

    this.changePassword();
  }

  private anyErrorExists(): boolean {
    this.resetGlobalError();

    if (this.isAnyErrorInFields()) {
      this.showAllErrors = true;
      return true;
    }
    return false;
  }

  private changePassword() {
    let linkFromMail = this.getCurrentUrl();
    let newPassword = this.getNewPasswordFromField();

    this.subscription = this.userService.restorePassword(linkFromMail, newPassword)
      .subscribe(result => {
        console.log(result);
        if (result.success) {
          this.doTransferToSuccessPage();
        }
        else {
          this.globalError = result.message;
        }
      });
  }

  resetGlobalError() {
    this.globalError = '';
  }

  getNewPasswordFromField(): string {
    return this.form.controls.newPassword.getRawValue();
  }

  getConfirmNewPasswordFromField(): string {
    return this.form.controls.confirmNewPassword.getRawValue();
  }

  isAnyErrorInFields(): boolean {
    let newPassword = this.getNewPasswordFromField();
    let confirmNewPassword = this.getConfirmNewPasswordFromField();
    if (newPassword != confirmNewPassword) {
      this.globalError = 'Новый пароль и его подтверждение не совпадают';
      return true;
    }

    return this.doesControlHaveError(this.form.controls.newPassword) ||
      this.doesControlHaveError(this.form.controls.confirmNewPassword);
  }

  doesControlHaveError(formControl: FormControl): boolean {
    return formControl.errors != null;
  }

  doTransferToSuccessPage() {
    this.router.navigate(['/successful-restoration']);
  }

  private getCurrentUrl() {
    return this.location.href;
  }
}
