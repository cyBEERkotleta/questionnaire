import {Component, EventEmitter, Input, Output} from '@angular/core';
import {INamed} from "../../additional/INamed";

@Component({
  selector: 'app-combobox-group',
  templateUrl: './combobox-group.component.html',
  styleUrls: ['./combobox-group.component.css']
})
export class ComboboxGroupComponent {
  @Input() label: string;
  @Input() required: boolean;
  @Input() objects: INamed[];

  @Output() itemSelection = new EventEmitter<INamed>();

  selectionChanged(item: INamed) {
    this.itemSelection.emit(item);
  }
}
