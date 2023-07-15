import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {ErrorService} from "./error.service";
import {Gender} from "../entity/Gender";
import {Form} from "../entity/Form";

@Injectable({
  providedIn: 'root'
})
export class GenderService {
  private http: HttpClient;
  private errorService: ErrorService;

  constructor(http: HttpClient, errorService: ErrorService) {
    this.http = http;
    this.errorService = errorService;
  }

  isGenderPresent(gender: Gender): boolean {
    return !!gender && !!gender.id && !!gender.name && !!gender.shownName;
  }

  getAll() : Observable<Gender[]> {
    return this.http.get<Gender[]>('http://localhost:8080/genders')
      .pipe(
        catchError(this.errorHandler.bind(this))
      )
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
