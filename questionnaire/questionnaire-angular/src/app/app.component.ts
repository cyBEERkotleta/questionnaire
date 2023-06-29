import { Component } from '@angular/core';
import {User} from "./User";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'questionnaire-angular';
  users!: User[];

  constructor(private httpClient: HttpClient) {
    httpClient.get<User[]>("http://localhost:8080/users").subscribe(result => {
      this.users = result;
    });
  }
}
