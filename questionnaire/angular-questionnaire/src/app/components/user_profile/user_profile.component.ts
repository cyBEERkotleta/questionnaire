import {Component, Input} from '@angular/core'
import {User} from "../../entity/User";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user_profile.component.html',
  styleUrls: ['./user_profile.component.css']
})
export class UserProfileComponent {
  @Input() user: User;

  isMale() : boolean {
    return this.user.gender.name == 'MALE';
  }

  isFemale() : boolean {
    return this.user.gender.name == 'FEMALE';
  }
}
