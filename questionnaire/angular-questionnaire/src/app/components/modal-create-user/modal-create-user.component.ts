import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-modal-create-user',
  templateUrl: './modal-create-user.component.html',
  styleUrls: ['./modal-create-user.component.css']
})
export class ModalCreateUserComponent {
  @Input() title: string;
}
