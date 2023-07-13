import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-successful-registration',
  templateUrl: './successful-registration.component.html',
  styleUrls: ['./successful-registration.component.css']
})
export class SuccessfulRegistrationComponent implements OnInit {
  private activatedRoute: ActivatedRoute;

  firstName: string;
  lastName: string;

  constructor(activatedRoute: ActivatedRoute) {
    this.activatedRoute = activatedRoute;
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.firstName = params['first_name'];
      this.lastName = params['last_name'];
    });
  }
}
