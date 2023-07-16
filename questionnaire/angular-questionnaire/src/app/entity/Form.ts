import {Field} from "./Field";
import {AnsweredForm} from "./AnsweredForm";

export class Form {
  id: bigint;
  name: string;
  fields: Field[];
  answeredForms: AnsweredForm[];
  shown: boolean;

  constructor(id: bigint, name: string, fields: Field[], answeredForms: AnsweredForm[], shown: boolean) {
    this.id = id;
    this.name = name;
    this.fields = fields;
    this.answeredForms = answeredForms;
    this.shown = shown;
  }
}
