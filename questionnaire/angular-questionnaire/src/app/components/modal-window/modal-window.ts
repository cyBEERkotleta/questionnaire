import {Component, Input} from '@angular/core';
import {ModalService} from "../../service/modal.service";

@Component({
  selector: 'app-modal-window',
  templateUrl: './modal-window.html',
  styleUrls: ['./modal-window.css']
})
export class ModalWindow {
  @Input() title: string;

  modalService: ModalService;

  constructor(modalService: ModalService) {
    this.modalService = modalService;
  }
}
