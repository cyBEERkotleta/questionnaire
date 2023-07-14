import { Component } from '@angular/core';
import {ModalService} from "../../service/modal.service";
import {FormService} from "../../service/form.service";
import {ActivatedRoute} from "@angular/router";
import {TopicService} from "../../service/topic.service";
import {Topic} from "../../entity/Topic";
import {Form} from "../../entity/Form";

@Component({
  selector: 'app-forms-page',
  templateUrl: './forms-page.component.html',
  styleUrls: ['./forms-page.component.css']
})
export class FormsPageComponent {
  private activatedRoute: ActivatedRoute;

  loading = false;
  private formsLoaded = false;
  private topicLoaded = false;

  term: string = '';

  formService: FormService;
  topicService: TopicService;
  modalService: ModalService;

  topic: Topic;
  forms: Form[];

  constructor(formService: FormService,
              topicService: TopicService,
              modalService: ModalService,
              activatedRoute: ActivatedRoute) {
    this.formService = formService;
    this.topicService = topicService;
    this.modalService = modalService;
    this.activatedRoute = activatedRoute;
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      let topicId = params['topic_id'];

      this.loading = true;

      this.topicService.getTopicById(topicId).subscribe(result => {
        this.topicLoaded = true;
        this.topic = result;
        this.checkLoading();
      });

      this.formService.getFormsByTopicId(topicId)
        .subscribe(result => {
          this.forms = result;
          this.formsLoaded = true;
          this.checkLoading();
        });
    });
  }

  private checkLoading() {
    if (this.formsLoaded && this.topicLoaded)
      this.loading = false;
  }
}
