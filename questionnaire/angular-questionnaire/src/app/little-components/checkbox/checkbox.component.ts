import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CheckBoxState} from "../../additional/CheckBoxState";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.css']
})
export class CheckboxComponent {
  @Input() text: string;

  @Output() itemSelection = new EventEmitter<CheckBoxState>();

  form = new FormGroup({
    check: new FormControl<boolean>(false)
  })

  selectionChanged() {
    let active = this.form.value.check;
    let checkBoxState = new CheckBoxState(this.text, active);
    this.itemSelection.emit(checkBoxState);
  }
}
