import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent {
  private activatedRoute: ActivatedRoute;

  firstName: string;
  lastName: string;
  email: string;

  private subscription: Subscription;
  constructor(activatedRoute: ActivatedRoute) {
    this.activatedRoute = activatedRoute;
  }

  ngOnInit() {
    this.subscription = this.activatedRoute.queryParams.subscribe(params => {
      this.firstName = params['first_name'];
      this.lastName = params['last_name'];
      this.email = params['email'];
    });
  }

  ngOnDestroy() {
    if (this.subscription)
      this.subscription.unsubscribe();
  }
}
