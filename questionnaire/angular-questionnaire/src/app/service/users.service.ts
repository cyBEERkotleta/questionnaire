import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, delay, Observable, throwError} from "rxjs";
import {User} from "../entity/User";
import {ErrorService} from "./error.service";

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

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
