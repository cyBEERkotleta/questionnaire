import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {Topic} from "../entity/Topic";
import {catchError, Observable, throwError} from "rxjs";
import {RequestResult} from "../additional/RequestResult";
import {SessionService} from "./session.service";
import {UserRole} from "../entity/UserRole";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
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

  isTopicPresent(topic: Topic): boolean {
    return !!topic && !!topic.id && !!topic.name && !!topic.description && !!topic.forms;
  }

  getAll() : Observable<Topic[]> {
    return this.http.get<Topic[]>('http://localhost:8080/topics')
      .pipe(
        catchError(this.errorHandler.bind(this)),
      )
  }

  getTopicById(id: bigint): Observable<Topic> {
    let path = 'http://localhost:8080/topics/' + id;
    return this.http.get<Topic>(path)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  saveTopic(topic: Topic): Observable<RequestResult> {
    let token = this.sessionService.getToken();
    let tokenWithTopic = {token: token, topic: topic};
    return this.http.post<RequestResult>('http://localhost:8080/save_topic', tokenWithTopic)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  deleteTopic(topic: Topic): Observable<RequestResult> {
    let token = this.sessionService.getToken();
    let tokenWithTopic = {token: token, topic: topic};
    return this.http.post<RequestResult>('http://localhost:8080/delete_topic', tokenWithTopic)
      .pipe(
        catchError(this.errorHandler.bind(this)),
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
