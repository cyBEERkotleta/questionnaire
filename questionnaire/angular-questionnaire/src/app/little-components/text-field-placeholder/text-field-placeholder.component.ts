import {Component, Input} from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-text-field-placeholder',
  templateUrl: './text-field-placeholder.component.html',
  styleUrls: ['./text-field-placeholder.component.css']
})
export class TextFieldPlaceholderComponent {
  @Input() placeholder: string;
  @Input() required: boolean;
  @Input() formElement: FormControl;

  getPlaceholder(): string {
    return this.placeholder + (this.required ? ' *' : '');
  }
}
