import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {Topic} from "../entity/Topic";
import {catchError, Observable, tap, throwError} from "rxjs";
import {User} from "../entity/User";
import {RequestResult} from "../additional/RequestResult";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private http: HttpClient;
  private errorService: ErrorService;

  topics: Topic[];
  topic: Topic;

  constructor(http: HttpClient, errorService: ErrorService) {
    this.http = http;
    this.errorService = errorService;
  }

  getAll() : Observable<Topic[]> {
    return this.http.get<Topic[]>('http://localhost:8080/topics')
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(topics => this.topics = topics)
      )
  }

  getTopicById(id: bigint): Observable<Topic> {
    let path = 'http://localhost:8080/topics/' + id;
    return this.http.get<Topic>(path)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(topic => this.topic = topic)
      );
  }

  saveTopic(topic: Topic): Observable<RequestResult> {
    return this.http.post<RequestResult>('http://localhost:8080/save_topic', topic)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(result => this.getAll().subscribe())
      );
  }

  deleteTopicById(id: bigint): Observable<RequestResult> {
    return this.http.post<RequestResult>('http://localhost:8080/delete_topic', id)
      .pipe(
        catchError(this.errorHandler.bind(this)),
        tap(result => this.getAll().subscribe())
      );
  }

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message);
    return throwError(() => error.message);
  }
}
