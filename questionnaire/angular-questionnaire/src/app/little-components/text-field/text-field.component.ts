import {Component, Input} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-text-field',
  templateUrl: './text-field.component.html',
  styleUrls: ['./text-field.component.css']
})
export class TextFieldComponent {
  @Input() fieldName: string;
  @Input() nextLine: boolean;
  @Input() formElement: FormControl;
  @Input() showError: boolean = false;

  isFieldRequired(): boolean {
    return this.formElement.hasValidator(Validators.required);
  }
}
