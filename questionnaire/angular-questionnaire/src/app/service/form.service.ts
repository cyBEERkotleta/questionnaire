import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {catchError, Observable, throwError} from "rxjs";
import {RequestResult} from "../additional/RequestResult";
import {Form} from "../entity/Form";
import {SessionService} from "./session.service";
import {Field} from "../entity/Field";
import {Topic} from "../entity/Topic";

@Injectable({
  providedIn: 'root'
})
export class FormService {
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

  isFormPresent(form: Form): boolean {
    return !!form && !!form.id && !!form.fields && !!form.name && !!form.answeredForms;
  }

  getAll(): Observable<Form[]> {
    return this.http.get<Form[]>('http://localhost:8080/forms')
      .pipe(
        catchError(this.errorHandler.bind(this))
      );
  }

  getFormsByTopicId(topicId: bigint) : Observable<Form[]> {
    let path = 'http://localhost:8080/forms/topic_' + topicId;
    return this.http.get<Form[]>(path)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  getFormsByUserId(userId: bigint) : Observable<Form[]> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/forms/user_' + userId;
    return this.http.post<Form[]>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  getFormById(id: bigint): Observable<Form> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/forms/' + id;
    return this.http.post<Form>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  saveForm(form: Form, topic: Topic): Observable<RequestResult> {
    let token = this.sessionService.getToken();
    let tokenWithFormAndTopic = {token: token, form: form, topic: topic};
    return this.http.post<RequestResult>('http://localhost:8080/save_form', tokenWithFormAndTopic)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  deleteForm(form: Form): Observable<RequestResult> {
    let token = this.sessionService.getToken();
    let tokenWithForm = {token: token, form: form};
    return this.http.post<RequestResult>('http://localhost:8080/delete_form', tokenWithForm)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
