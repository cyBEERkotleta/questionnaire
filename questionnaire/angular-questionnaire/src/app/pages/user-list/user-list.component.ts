import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ModalService} from "../../service/modal.service";
import {User} from "../../entity/User";
import {SessionService} from "../../service/session.service";

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

  users: User[];

  constructor(userService: UserService,
              modalService: ModalService) {
    this.userService = userService;
    this.modalService = modalService;
  }

  ngOnInit() {
    this.loading = true;

    this.userService.getAll()
      .subscribe(result => {
        this.users = result;
        this.loading = false;
      });
  }
}
