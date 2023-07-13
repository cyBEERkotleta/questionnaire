import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {catchError, Observable, tap, throwError} from "rxjs";
import {RequestResult} from "../additional/RequestResult";
import {Form} from "../entity/Form";

@Injectable({
  providedIn: 'root'
})
export class FormService {
  private http: HttpClient;
  private errorService: ErrorService;

  forms: Form[];
  form: Form;

  constructor(http: HttpClient, errorService: ErrorService) {
    this.http = http;
    this.errorService = errorService;
  }

  getFormsByTopicId(topicId: bigint) : Observable<Form[]> {
    let path = 'http://localhost:8080/forms/topic_' + topicId;
    return this.http.get<Form[]>(path)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(forms => this.forms = forms)
      )
  }

  getFormsByUserId(userId: bigint) : Observable<Form[]> {
    let path = 'http://localhost:8080/forms/user_' + userId;
    return this.http.get<Form[]>(path)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(forms => this.forms = forms)
      )
  }

  getFormById(id: bigint): Observable<Form> {
    let path = 'http://localhost:8080/forms/' + id;
    return this.http.get<Form>(path)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(form => this.form = form)
      );
  }

  saveForm(form: Form): Observable<RequestResult> {
    return this.http.post<RequestResult>('http://localhost:8080/save_form', form)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  deleteFormById(id: bigint): Observable<RequestResult> {
    return this.http.post<RequestResult>('http://localhost:8080/delete_form', id)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
