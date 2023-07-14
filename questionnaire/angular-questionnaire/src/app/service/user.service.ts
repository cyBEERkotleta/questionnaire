import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, tap, throwError} from "rxjs";
import {User} from "../entity/User";
import {ErrorService} from "./error.service";
import {AuthorizeResult} from "../additional/AuthorizeResult";
import {SessionService} from "./session.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private http: HttpClient;
  private errorService: ErrorService;
  private sessionService: SessionService;

  constructor(http: HttpClient,
              errorService: ErrorService,
              sessionService: SessionService) {
    this.http = http;
    this.errorService = errorService;
    this.sessionService = sessionService;
  }

  getAll() : Observable<User[]> {
    let token = this.sessionService.getToken();
    return this.http.post<User[]>('http://localhost:8080/users', token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      )
  }

  getUserById(id: bigint) : Observable<User> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/users/' + id;
    return this.http.post<User>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      )
  }

  register(user: User, password: string): Observable<AuthorizeResult> {
    const userWithPassword = {user: user, password: password};

    return this.http.post<AuthorizeResult>('http://localhost:8080/register', userWithPassword)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(result => {
          if (result.success) {
            this.updateTokenInSession(result.token);
          }
        })
      );
  }

  login(email: string, password: string, rememberMe: boolean): Observable<AuthorizeResult> {
    const loginData = {email: email, password: password};

    return this.http.post<AuthorizeResult>('http://localhost:8080/login', loginData)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(result => {
          if (result.success) {
            this.updateTokenWithRememberMeFlag(result.token, rememberMe);
          }
        })
      );
  }

  changePassword(oldPassword: string, newPassword: string): Observable<AuthorizeResult> {
    let token = this.sessionService.getToken();
    const tokenWithChangePasswordData = {
      token: token, oldPassword: oldPassword, newPassword: newPassword
    };

    return this.http.post<AuthorizeResult>('http://localhost:8080/change_password', tokenWithChangePasswordData)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(result => {
          if (result.success) {
            this.updateTokenWhereItWasSet(result.token);
          }
        })
      );
  }

  saveUser(user: User): Observable<AuthorizeResult> {
    let token = this.sessionService.getToken();
    const tokenWithUser = {
      token: token, user: user
    };

    return this.http.post<AuthorizeResult>('http://localhost:8080/save_user', tokenWithUser)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(result => {
          if (result.success) {
            this.updateTokenWhereItWasSet(result.token);
          }
        })
      );
  }

  getCurrentUser(): Observable<User> {
    let token = this.sessionService.getToken();
    return this.http.post<User>('http://localhost:8080/user_by_token', token)
      .pipe(
        catchError(this.errorHandler.bind(this))
      )
  }

  private updateTokenWithRememberMeFlag(token: string, rememberMe: boolean) {
    if (rememberMe)
      this.updateTokenGlobally(token);
    else
      this.updateTokenGlobally(token);
  }

  private updateTokenInSession(token: string) {
    this.sessionService.saveTokenToSessionStorage(token);
  }

  private updateTokenGlobally(token: string) {
    this.sessionService.saveTokenToSessionStorage(token);
    this.sessionService.saveTokenToLocalStorage(token);
  }

  private updateTokenWhereItWasSet(token: string) {
    this.sessionService.updateTokenWhereItWasSet(token);
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
