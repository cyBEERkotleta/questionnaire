import {FieldType} from "./FieldType";

export class Field {
  id: bigint;
  label: string;
  fieldType: FieldType;
  required: boolean;
  active: boolean;

  constructor(id: bigint, label: string, fieldType: FieldType, required: boolean, active: boolean) {
    this.id = id;
    this.label = label;
    this.fieldType = fieldType;
    this.required = required;
    this.active = active;
  }
}
