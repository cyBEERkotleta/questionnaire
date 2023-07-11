import { Component } from '@angular/core';
import {UserService} from "../../service/users.service";
import {ModalService} from "../../service/modal.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  title = 'angular-questionnaire';
  loading = false;
  term: string = '';

  userService: UserService;
  modalService: ModalService;

  constructor(userService: UserService, modalService: ModalService) {
    this.userService = userService;
    this.modalService = modalService;
  }

  ngOnInit() {
    this.loading = true;

    this.userService.getAll()
      .subscribe(() => {
        this.loading = false;
      });
  }
}