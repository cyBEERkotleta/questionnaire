import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {User} from "../entity/User";
import {ErrorService} from "./error.service";
import {RequestResult} from "../additional/RequestResult";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private http: HttpClient;
  private errorService: ErrorService;

  constructor(http: HttpClient, errorService: ErrorService) {
    this.http = http;
    this.errorService = errorService;
  }

  getAll() : Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/users')
      .pipe(
        catchError(this.errorHandler.bind(this))
      )
  }

  /*register(email: string, password: string, firstName: string, lastName: string,
           phoneNumber: string, gender: Gender) : Observable<RequestResult> {
    return this.http.post<RequestResult>('http://localhost:8080/register')
      .pipe(
        catchError(this.errorHandler.bind(this))
      )
  }*/

  register(user: User, password: string) : Observable<RequestResult> {
    let headers = { 'content-type': 'application/json'};
    let body = JSON.stringify(user) + JSON.stringify(password);
    console.log(body);
    return this.http.post<RequestResult>('http://localhost:8080/register', body, {'headers':headers})
      .pipe(
        catchError(this.errorHandler.bind(this))
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
