import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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
  @Input() selected: INamed = null;

  @Output() itemSelection = new EventEmitter<INamed>();

  selectionChanged(item: INamed) {
    this.selected = item;
    this.itemSelection.emit(item);
    console.log('selectionChanged(' + item.shownName + ') in combobox-group');
  }

  isChecked(item: INamed) {
    return !!this.selected && this.selected.shownName == item.shownName;
  }
}
