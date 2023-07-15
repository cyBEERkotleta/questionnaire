import { Injectable } from '@angular/core';
import {User} from "../entity/User";
import {RoleService} from "./role.service";

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private TOKEN_KEY = 'token';
  private TOKEN_BY_DEFAULT = 'no_token';

  private user: User;

  private roleService: RoleService;

  constructor(roleService: RoleService) {
    this.roleService = roleService;
  }

  setUser(user: User) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }

  isCurrentUserAuthorized(): boolean {
    return !!this.user;
  }

  isCurrentUserAdmin(): boolean {
    if (!this.isCurrentUserAuthorized())
      return false;
    return this.roleService.areRolesEqual(this.user.userRole, this.roleService.adminRole);
  }

  isCurrentUserMember(): boolean {
    if (!this.isCurrentUserAuthorized())
      return false;
    return this.roleService.areRolesEqual(this.user.userRole, this.roleService.memberRole);
  }

  resetUser() {
    this.user = null;
  }

  ping() {
    console.log('sessionStorage token=' + sessionStorage.getItem(this.TOKEN_KEY));
    console.log('localStorage token=' + localStorage.getItem(this.TOKEN_KEY));
  }

  saveTokenToLocalStorage(token: string){
    localStorage.setItem(this.TOKEN_KEY, token);

    console.log('method saveTokenToLocalStorage');
    this.ping();
  }

  saveTokenToSessionStorage(token: string) {
    sessionStorage.setItem(this.TOKEN_KEY, token);

    console.log('method saveTokenToSessionStorage');
    this.ping();
  }

  updateTokenWhereItWasSet(token: string) {
    console.log('method updateTokenWhereItWasSet');
    this.ping();

    if (this.getTokenFromSessionStorage() != this.TOKEN_BY_DEFAULT)
      this.saveTokenToSessionStorage(token);
    if (this.getTokenFromLocalStorage() != this.TOKEN_BY_DEFAULT)
      this.saveTokenToLocalStorage(token);

    this.ping();
  }

  getTokenFromLocalStorage() {
    console.log('method getTokenFromLocalStorage');
    this.ping();

    let token = localStorage.getItem(this.TOKEN_KEY);
    return token ? token : this.TOKEN_BY_DEFAULT;
  }

  getTokenFromSessionStorage() {
    console.log('method getTokenFromSessionStorage');
    this.ping();

    let token = sessionStorage.getItem(this.TOKEN_KEY);
    return token ? token : this.TOKEN_BY_DEFAULT;
  }

  getToken() {
    console.log('method getToken');
    this.ping();

    let token = this.getTokenFromSessionStorage();
    if (token && token != this.TOKEN_BY_DEFAULT) {
      console.log('token: ' + token);
      return token;
    }
    token = this.getTokenFromLocalStorage();
    if (token && token != this.TOKEN_BY_DEFAULT) {
      this.saveTokenToSessionStorage(token);
      console.log('token: ' + token);
      return token;
    }
    console.log('token: ' + token);
    return this.TOKEN_BY_DEFAULT;
  }

  removeTokenFromLocalStorage(){
    console.log('method removeTokenFromLocalStorage');
    this.ping();

    localStorage.removeItem(this.TOKEN_KEY);

    this.ping();
  }

  removeTokenFromSessionStorage(){
    console.log('method removeTokenFromSessionStorage');
    this.ping();

    sessionStorage.removeItem(this.TOKEN_KEY);

    this.ping();
  }

  removeTokenFromEverywhere(){
    console.log('method removeTokenFromEverywhere');
    this.removeTokenFromSessionStorage();
    this.removeTokenFromLocalStorage();
  }
}
