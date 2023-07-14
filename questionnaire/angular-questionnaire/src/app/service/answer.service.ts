import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {SessionService} from "./session.service";
import {catchError, Observable, throwError} from "rxjs";
import {RequestResult} from "../additional/RequestResult";
import {Answer} from "../entity/Answer";

@Injectable({
  providedIn: 'root'
})
export class AnswerService {
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

  getAnswerById(id: bigint): Observable<Answer> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/answers/' + id;
    return this.http.post<Answer>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  getAnswersByAnsweredFormId(answeredFormId: bigint) : Observable<Answer[]> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/fields/answered_form_' + answeredFormId;
    return this.http.post<Answer[]>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  getAnswersByFieldId(fieldId: bigint) : Observable<Answer[]> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/fields/field_' + fieldId;
    return this.http.post<Answer[]>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  saveAnswer(answer: Answer): Observable<RequestResult> {
    return this.http.post<RequestResult>('http://localhost:8080/save_answer', answer)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
