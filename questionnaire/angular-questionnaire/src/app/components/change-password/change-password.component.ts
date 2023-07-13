import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  private userService: UserService;
  private router: Router;

  showAllErrors = false;
  globalError: string = '';
  private successfulChange = false;

  form = new FormGroup({
    oldPassword: new FormControl<string>('', [
      Validators.required
    ]),
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
              router: Router) {
    this.userService = userService;
    this.router = router;
  }

  ngOnInit() {
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
    let oldPassword = this.getOldPasswordFromField();
    let newPassword = this.getNewPasswordFromField();

    const email = 'eeekotletka@gmail.com';

    this.userService.changePassword(email, oldPassword, newPassword)
      .subscribe(result => {
        console.log(result);
        if (result.success) {
          this.successfulChange = true;
          this.doTransferToSuccessPage();
        }
        else {
          this.globalError = result.message;
          this.successfulChange = false;
        }
      });
  }

  resetGlobalError() {
    this.globalError = '';
  }

  getOldPasswordFromField(): string {
    return this.form.controls.oldPassword.getRawValue();
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

    return this.doesControlHaveError(this.form.controls.oldPassword) ||
      this.doesControlHaveError(this.form.controls.newPassword) ||
      this.doesControlHaveError(this.form.controls.confirmNewPassword);
  }

  doesControlHaveError(formControl: FormControl): boolean {
    return formControl.errors != null;
  }

  doTransferToSuccessPage() {
    this.router.navigate(['/successful-password-change']);
  }

  doTransferToMainPage() {
    this.router.navigate(['']);
  }
}
