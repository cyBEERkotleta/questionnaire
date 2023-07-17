import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {WebSocketService} from "../../service/web-socket.service";
import {AnsweredForm} from "../../entity/AnsweredForm";
import {Subscription} from "rxjs";
import {AnsweredFormService} from "../../service/answered-form.service";
import {MessagingService} from "../../service/messaging-service.service";

@Component({
  selector: 'app-manage-answers',
  templateUrl: './manage-answers.component.html',
  styleUrls: ['./manage-answers.component.css']
})
export class ManageAnswersComponent implements OnInit, OnDestroy {
  @Input() formId: bigint;

  answeredForms: AnsweredForm[];

  private answeredFormService: AnsweredFormService;
  private webSocketService: WebSocketService;

  loading = false;

  private subscriptionNewData: Subscription;
  private subscriptionWebSocket: Subscription;

  constructor(answeredFormService: AnsweredFormService,
              webSocketService: WebSocketService) {
    this.answeredFormService = answeredFormService;
    this.webSocketService = webSocketService;
  }

  ngOnInit() {
    this.loadAnswers();

    this.subscriptionWebSocket = this.webSocketService
      .subscribeToFormUpdates(this.formId)
      .subscribe(result => {
        this.loading = true;
        this.answeredForms = result;
        this.loading = false;
    });
  }

  ngOnDestroy() {
    if (this.subscriptionNewData)
      this.subscriptionNewData.unsubscribe();
    if (this.subscriptionWebSocket)
      this.subscriptionWebSocket.unsubscribe();
  }

  private loadAnswers() {
    this.loading = true;

    if (this.subscriptionNewData)
      this.subscriptionNewData.unsubscribe();

    this.subscriptionNewData = this.answeredFormService.getAnsweredFormsByFormId(this.formId)
      .subscribe(result => {
        this.answeredForms = result;

        this.loading = false;
      });
  }
}
