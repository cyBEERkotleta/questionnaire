import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-text-field-placeholder',
  templateUrl: './text-field-placeholder.component.html',
  styleUrls: ['./text-field-placeholder.component.css']
})
export class TextFieldPlaceholderComponent {
  @Input() placeholder: string;
  @Input() required: boolean;

  getPlaceholder(): string {
    return this.placeholder + (this.required ? ' *' : '');
  }
}
