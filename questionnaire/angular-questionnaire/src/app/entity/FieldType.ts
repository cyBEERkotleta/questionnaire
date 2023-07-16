export class FieldType {
  id: number;
  name: string;
  ableToHaveOptions: boolean;

  constructor(id: number, name: string, ableToHaveOptions: boolean) {
    this.id = id;
    this.name = name;
    this.ableToHaveOptions = ableToHaveOptions;
  }
}
