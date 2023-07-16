import {Component, Input} from '@angular/core';
import {Form} from "@angular/forms";
import {Topic} from "../../entity/Topic";

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})
export class EditFormComponent {
  @Input() topic: Topic;
  @Input() form: Form;
}
