import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Gender} from "../../entity/Gender";
import {UserService} from "../../service/users.service";
import {User} from "../../entity/User";
import {ModalService} from "../../service/modal.service";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  private userService: UserService;
  private modalService: ModalService;
  showAllErrors = false;
  globalError: string = '';

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
      Validators.pattern('\\d{3,15}')
    ]),
    gender: new FormControl<Gender>(null, [
      Validators.required
    ])
  })

  constructor(userService: UserService, modalService: ModalService) {
    this.userService = userService;
    this.modalService = modalService;
  }

  ngOnInit() {
  }

  isGlobalErrorSet(): boolean {
    return this.globalError != '';
  }

  submit() {
    this.resetGlobalError();

    if (this.isAnyErrorInFields()) {
      if (!this.doPasswordsMatch())
        this.globalError = 'Пароли не совпадают';

      this.showAllErrors = true;
      return;
    }
    let user = this.createUserFromFields();
    let password = this.getPasswordFromField();

    console.log(user);
    console.log(password);

    this.userService.register(user, password).subscribe(result => {
      console.log(result);
      if (result.success) {
        this.modalService.close();
      } else {
        this.globalError = result.message;
      }
    });
  }

  resetGlobalError() {
    this.globalError = '';
  }

  createUserFromFields(): User {
    let email = this.form.controls.email.getRawValue();
    let firstName = this.form.controls.firstName.getRawValue();
    let lastName = this.form.controls.lastName.getRawValue();
    let phoneNumber = this.form.controls.phoneNumber.getRawValue();
    let gender = this.form.controls.gender.getRawValue();

    return new User(null, email, firstName, lastName, phoneNumber,
      null, null, gender);
  }

  getPasswordFromField(): string {
    return this.form.controls.password.getRawValue();
  }

  isAnyErrorInFields(): boolean {
    return this.doesControlHaveError(this.form.controls.email) ||
      this.doesControlHaveError(this.form.controls.password) ||
      this.doesControlHaveError(this.form.controls.confirmPassword) ||
      this.doesControlHaveError(this.form.controls.firstName) ||
      this.doesControlHaveError(this.form.controls.lastName) ||
      this.doesControlHaveError(this.form.controls.phoneNumber) ||
      this.doesControlHaveError(this.form.controls.gender);
  }

  doesControlHaveError(formControl: FormControl): boolean {
    return formControl.errors != null;
  }

  doPasswordsMatch(): boolean {
    let password = this.form.controls.password.getRawValue();
    let confirmPassword = this.form.controls.confirmPassword.getRawValue();
    return password == confirmPassword;
  }
}
