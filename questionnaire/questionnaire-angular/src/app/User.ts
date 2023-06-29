export class UserRole {
  id: bigint;
  name: string;

  constructor(id: bigint, name: string) {
    this.id = id;
    this.name = name;
  }

  get id(): bigint {
    return this.id;
  }
  set id(id: bigint) {
    this.id = id;
  }

}

export class User {
  id: bigint;
  email: string;
  hashedPassword: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  role: UserRole;
}
