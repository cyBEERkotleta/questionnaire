import {Component, OnInit} from '@angular/core';
import {User} from "./entity/User";
import {UserService} from "./service/users.service";
import {ModalService} from "./service/modal.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'angular-questionnaire';
  users: User[];
  loading = false;
  term: string = '';

  private userService: UserService;
  modalService: ModalService;

  constructor(userService: UserService, modalService: ModalService) {
    this.userService = userService;
    this.modalService = modalService;
  }

  ngOnInit() {
    this.loading = true;

    this.userService.getAll().subscribe(users => {
      this.users = users;

      this.loading = false;
    });
  }
}
