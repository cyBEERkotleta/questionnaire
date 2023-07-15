import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {SessionService} from "./session.service";
import {catchError, Observable, throwError} from "rxjs";
import {RequestResult} from "../additional/RequestResult";
import {Field} from "../entity/Field";
import {AnsweredForm} from "../entity/AnsweredForm";

@Injectable({
  providedIn: 'root'
})
export class FieldService {
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

  isFieldPresent(field: Field): boolean {
    return !!field && !!field.id && !!field.fieldType && !!field.active && !!field.required && !!field.label;
  }

  getFieldById(id: bigint): Observable<Field> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/fields/' + id;
    return this.http.post<Field>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  getFieldsByFormId(formId: bigint) : Observable<Field[]> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/fields/form_' + formId;
    return this.http.post<Field[]>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  getActiveFieldsByFormId(formId: bigint) : Observable<Field[]> {
    let token = this.sessionService.getToken();
    let path = 'http://localhost:8080/fields_active/form_' + formId;
    return this.http.post<Field[]>(path, token)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  saveForm(field: Field): Observable<RequestResult> {
    let token = this.sessionService.getToken();
    let tokenWithField = {token: token, field: field};
    return this.http.post<RequestResult>('http://localhost:8080/save_field', tokenWithField)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  deleteForm(field: Field): Observable<RequestResult> {
    let token = this.sessionService.getToken();
    let tokenWithField = {token: token, field: field};
    return this.http.post<RequestResult>('http://localhost:8080/delete_field', tokenWithField)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
