import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Gender} from "../../entity/Gender";
import {UserService} from "../../service/user.service";
import {User} from "../../entity/User";
import {NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {
  private userService: UserService;
  private router: Router;

  private createdUser: User;

  showAllErrors = false;
  globalError: string = '';
  private successfulReg = false;

  form = new FormGroup({
    email: new FormControl<string>('', [
      Validators.email,
      Validators.minLength(5),
      Validators.maxLength(60),
      Validators.required
    ]),
    password: new FormControl<string>('', [
      Validators.minLength(8),
      Validators.maxLength(30),
      Validators.required
    ]),
    confirmPassword: new FormControl<string>('', [
      Validators.minLength(8),
      Validators.maxLength(30),
      Validators.required
    ]),
    firstName: new FormControl<string>('', [
      Validators.minLength(2),
      Validators.maxLength(30),
      Validators.required
    ]),
    lastName: new FormControl<string>('', [
      Validators.minLength(2),
      Validators.maxLength(30),
      Validators.required
    ]),
    phoneNumber: new FormControl<string>('', [
      Validators.required,
      Validators.pattern('\\d{3,15}')
    ]),
    gender: new FormControl<Gender>(null, [
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

    this.registerUser();
  }

  private anyErrorExists(): boolean {
    this.resetGlobalError();
    let passwordsMatch = this.doPasswordsMatch();

    if (this.isAnyErrorInFields() || !passwordsMatch) {
      if (!passwordsMatch) {
        this.globalError = 'Пароли не совпадают';
      }
      this.showAllErrors = true;
      return true;
    }
    return false;
  }

  private registerUser() {
    this.createdUser = this.createUserFromFields();
    let password = this.getPasswordFromField();

    this.userService.register(this.createdUser, password)
      .subscribe(result => {
        console.log(result);

        if (result.success) {
          this.successfulReg = true;
          this.navigateToSuccessfulRegistrationPage();
        }
        else {
          this.successfulReg = false;
          this.globalError = result.message;
        }
      });
  }

  private navigateToSuccessfulRegistrationPage() {
    let navigationExtras: NavigationExtras = {
      queryParams: {'first_name': this.createdUser.firstName,
                    'last_name': this.createdUser.lastName}
    };

    this.router.navigate(['/successful-registration'], navigationExtras);
  }

  private resetGlobalError() {
    this.globalError = '';
  }

  private createUserFromFields(): User {
    let email = this.form.controls.email.getRawValue();
    let firstName = this.form.controls.firstName.getRawValue();
    let lastName = this.form.controls.lastName.getRawValue();
    let phoneNumber = this.form.controls.phoneNumber.getRawValue();
    let gender = this.form.controls.gender.getRawValue();

    return new User(null, email, firstName, lastName, phoneNumber,
      null, null, gender);
  }

  private getPasswordFromField(): string {
    return this.form.controls.password.getRawValue();
  }

  private isAnyErrorInFields(): boolean {
    return this.doesControlHaveError(this.form.controls.email) ||
      this.doesControlHaveError(this.form.controls.password) ||
      this.doesControlHaveError(this.form.controls.confirmPassword) ||
      this.doesControlHaveError(this.form.controls.firstName) ||
      this.doesControlHaveError(this.form.controls.lastName) ||
      this.doesControlHaveError(this.form.controls.phoneNumber) ||
      this.doesControlHaveError(this.form.controls.gender);
  }

  private doesControlHaveError(formControl: FormControl): boolean {
    return formControl.errors != null;
  }

  private doPasswordsMatch(): boolean {
    let password = this.form.controls.password.getRawValue();
    let confirmPassword = this.form.controls.confirmPassword.getRawValue();
    return password == confirmPassword;
  }
}
