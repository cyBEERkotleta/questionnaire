import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-text-field',
  templateUrl: './text-field.component.html',
  styleUrls: ['./text-field.component.css']
})
export class TextFieldComponent {
  @Input() fieldName: string;
  @Input() required: boolean;
  @Input() nextLine: boolean;
}
