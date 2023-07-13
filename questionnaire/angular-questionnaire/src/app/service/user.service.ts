import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, tap, throwError} from "rxjs";
import {User} from "../entity/User";
import {ErrorService} from "./error.service";
import {RequestResult} from "../additional/RequestResult";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private http: HttpClient;
  private errorService: ErrorService;

  users: User[] = [];

  constructor(http: HttpClient, errorService: ErrorService) {
    this.http = http;
    this.errorService = errorService;
  }

  getAll() : Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/users')
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(users => this.users = users)
      )
  }

  register(user: User, password: string): Observable<RequestResult> {
    const userWithPassword = {user: user, password: password};

    return this.http.post<RequestResult>('http://localhost:8080/register', userWithPassword)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(result => this.getAll().subscribe())
      );
  }

  login(email: string, password: string): Observable<RequestResult> {
    const loginData = {email: email, password: password};

    return this.http.post<RequestResult>('http://localhost:8080/login', loginData)
      .pipe(
        catchError(this.errorHandler.bind(this))
      );
  }

  changePassword(email: string, oldPassword: string, newPassword: string): Observable<RequestResult> {
    const changePasswordData = {email: email, oldPassword: oldPassword, newPassword: newPassword};

    return this.http.post<RequestResult>('http://localhost:8080/change_password', changePasswordData)
      .pipe(
        catchError(this.errorHandler.bind(this))
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}