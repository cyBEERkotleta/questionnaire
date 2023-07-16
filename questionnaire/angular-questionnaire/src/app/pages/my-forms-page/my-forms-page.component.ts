import { Component } from '@angular/core';
import {ModalEditWindowService} from "../../service/modal-edit-window.service";
import {ModalCreateWindowService} from "../../service/modal-create-window.service";

@Component({
  selector: 'app-my-forms-page',
  templateUrl: './my-forms-page.component.html',
  styleUrls: ['./my-forms-page.component.css']
})
export class MyFormsPageComponent {
  modalCreateWindow: ModalCreateWindowService;
  modalEditWindow: ModalEditWindowService;

  constructor(modalCreateWindow: ModalCreateWindowService,
              modalEditWindow: ModalEditWindowService) {
    this.modalCreateWindow = modalCreateWindow;
    this.modalEditWindow = modalEditWindow;
  }

}
