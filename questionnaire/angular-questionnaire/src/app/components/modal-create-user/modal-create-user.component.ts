import {Component, Input} from '@angular/core';
import {ModalService} from "../../service/modal.service";

@Component({
  selector: 'app-modal-create-user',
  templateUrl: './modal-create-user.component.html',
  styleUrls: ['./modal-create-user.component.css']
})
export class ModalCreateUserComponent {
  @Input() title: string;

  modalService: ModalService;

  constructor(modalService: ModalService) {
    this.modalService = modalService;
  }
}
