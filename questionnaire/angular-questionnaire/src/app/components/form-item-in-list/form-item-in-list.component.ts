import {Component, Input} from '@angular/core';
import {Form} from "../../entity/Form";

@Component({
  selector: 'app-form-item-in-list',
  templateUrl: './form-item-in-list.component.html',
  styleUrls: ['./form-item-in-list.component.css']
})
export class FormItemInListComponent {
  @Input() form: Form;
}
