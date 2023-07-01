import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./entity/User";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private http: HttpClient;

  title = 'angular-questionnaire';
  users: User[];

  constructor(http: HttpClient) {
    this.http = http;
    this.subscribeForUserData();
  }

  private subscribeForUserData() {
    this.http.get<User[]>("http://localhost:8080/users").subscribe(result => {
      this.users = result;
    });
  }
}
