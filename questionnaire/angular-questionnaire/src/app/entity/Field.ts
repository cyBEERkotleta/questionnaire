import {FieldType} from "./FieldType";
import {FieldOption} from "./FieldOption";

export class Field {
  id: bigint;
  label: string;
  fieldType: FieldType;
  required: boolean;
  active: boolean;
  options: FieldOption[];

  constructor(id: bigint, label: string, fieldType: FieldType, required: boolean, active: boolean, options: FieldOption[]) {
    this.id = id;
    this.label = label;
    this.fieldType = fieldType;
    this.required = required;
    this.active = active;
    this.options = options;
  }
}
