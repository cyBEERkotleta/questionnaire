import {Component, Input} from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-text-field',
  templateUrl: './text-field.component.html',
  styleUrls: ['./text-field.component.css']
})
export class TextFieldComponent {
  @Input() fieldName: string;
  @Input() required: boolean;
  @Input() nextLine: boolean;
  @Input() formElement: FormControl;
}
