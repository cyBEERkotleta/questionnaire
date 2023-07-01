import {Form} from "./Form";

export class Topic {
  id: bigint;
  name: string;
  description: string;
  forms: Form[];

  constructor(id: bigint, name: string, description: string, forms: Form[]) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.forms = forms;
  }
}
