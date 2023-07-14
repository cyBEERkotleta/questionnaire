import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private TOKEN_KEY = 'token';
  private TOKEN_BY_DEFAULT = 'no_token';

  saveTokenToLocalStorage(token: string){
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  saveTokenToSessionStorage(token: string) {
    sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  updateTokenWhereItWasSet(token: string) {
    if (this.getTokenFromSessionStorage() != this.TOKEN_BY_DEFAULT)
      this.saveTokenToSessionStorage(token);
    if (this.getTokenFromLocalStorage() != this.TOKEN_BY_DEFAULT)
      this.saveTokenToLocalStorage(token);
  }

  getTokenFromLocalStorage() {
    let token = localStorage.getItem(this.TOKEN_KEY);
    return token ? token : this.TOKEN_BY_DEFAULT;
  }

  getTokenFromSessionStorage() {
    let token = sessionStorage.getItem(this.TOKEN_KEY);
    return token ? token : this.TOKEN_BY_DEFAULT;
  }

  getToken() {
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
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
