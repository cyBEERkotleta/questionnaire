import {Form} from "./Form";
import {UserRole} from "./UserRole";
import {Gender} from "./Gender";

export class User {
  id: bigint;
  email: string;
  hashedPassword: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  userRole: UserRole;
  forms: Form[];
  gender: Gender;

  constructor(id: bigint, email: string, hashedPassword: string, firstName: string, lastName: string, phoneNumber: string, userRole: UserRole, forms: Form[], gender: Gender) {
    this.id = id;
    this.email = email;
    this.hashedPassword = hashedPassword;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.userRole = userRole;
    this.forms = forms;
    this.gender = gender;
  }
}
