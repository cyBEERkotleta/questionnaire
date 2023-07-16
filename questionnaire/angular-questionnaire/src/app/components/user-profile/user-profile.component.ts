import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../entity/User";
import {UserService} from "../../service/user.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit, OnDestroy {
  @Input() user: User;

  private userService: UserService;
  private sessionUser: User;

  private subscription: Subscription;

  constructor(userService: UserService) {
    this.userService = userService;
  }

  ngOnInit() {
    this.subscription = this.userService.updateCurrentUser()
      .subscribe(result => {
        this.sessionUser = result;
      });
  }

  ngOnDestroy() {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  isMale() : boolean {
    if (!this.user)
      return true;
    return this.user.gender.name == 'MALE';
  }

  isFemale() : boolean {
    if (!this.user)
      return false;
    return this.user.gender.name == 'FEMALE';
  }

  isProfileOwn(): boolean {
    if (this.sessionUser && this.user) {
      return (this.sessionUser.id == this.user.id);
    } else {
      return false;
    }
  }

  getUserName(): string {
    if (!this.user)
      return ''
    return this.user.firstName + ' ' + this.user.lastName;
  }

  getUserEmail(): string {
    if (!this.user)
      return '';
    return this.user.email;
  }

  getUserPhone(): string {
    if (!this.user)
      return '';
    return this.user.phoneNumber;
  }

  getFormCount(): number {
    if (!this.user)
      return 0;
    return this.user.forms.length;
  }

  getUserGenderName(): string {
    if (!this.user)
      return '';
    return this.user.gender.shownName;
  }
}
