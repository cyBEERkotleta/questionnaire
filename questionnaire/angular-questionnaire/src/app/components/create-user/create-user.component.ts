import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Gender} from "../../entity/Gender";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
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
      Validators.pattern('\d{3,15}')
    ]),
    gender: new FormControl<Gender>(new Gender(1, ''), [
      Validators.required
    ])
  })

  constructor() {
  }

  ngOnInit() {
  }

  submit() {
    console.log(this.form.value);
  }

  protected readonly FormControl = FormControl;
}
