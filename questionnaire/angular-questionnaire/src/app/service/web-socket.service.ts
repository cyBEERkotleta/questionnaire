import { Injectable } from '@angular/core';
import {MessagingService} from "./messaging-service.service";
import {map, Observable} from "rxjs";
import {AnsweredForm} from "../entity/AnsweredForm";
import * as StompJS from '@stomp/stompjs';

const { Client } = StompJS;

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  /*private path = 'ws://localhost:8080/ws';

  subscribe(formId: bigint, callback: any): StompSubscription {
      this.stompClient.activate();
    return this.subscribeToForm(formId, callback);
  }

  private subscribeToForm(formId: bigint, callback: any): StompSubscription {
    return this.stompClient.subscribe(this.getDestination(formId), (message) => {
      console.log('Received message:', JSON.stringify(message.body));
    });
  }*/

  private path = 'ws://localhost:8080/ws';

  private messagingService;

  constructor() {

    this.stompClient.activate();
  }

  subscribeToFormUpdates(formId: bigint): Observable<AnsweredForm[]> {
    this.messagingService = new MessagingService(this.path, this.getDestination(formId));
    return this.messagingService.stream()
      .pipe(
      map(
        message => {
          return JSON.parse(message.toString()) as AnsweredForm[];
        }
      )
    );
  }

  private getDestination(formId: bigint): string {
    return '/user/answered_forms_queue/' + formId;
  }

  private stompClient = new StompJS.Client({
    brokerURL: 'ws://localhost:8080/ws'
  });

  private setEvents() {
    this.stompClient.onConnect = (frame) => {
      console.log('Connected: ' + frame);
      this.stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log(JSON.parse(greeting.body).content);
      });
    };

    this.stompClient.onWebSocketError = (error) => {
      console.error('Error with websocket', error);
    };

    this.stompClient.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

  }
}
