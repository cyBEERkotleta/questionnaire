import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-finish-registration',
  templateUrl: './finish-registration.component.html',
  styleUrls: ['./finish-registration.component.css']
})
export class FinishRegistrationComponent implements OnInit, OnDestroy {
  private userService: UserService;
  private location: Location;

  private subscription: Subscription;

  constructor(userService: UserService,
              location: Location) {
    this.userService = userService;
    this.location = location;
  }

  ngOnInit() {

  }

  ngOnDestroy() {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  private getCurrentUrl() {
    return this.location.href;
  }
}
