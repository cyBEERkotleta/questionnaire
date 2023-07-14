import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ModalService} from "../../service/modal.service";
import {TopicService} from "../../service/topic.service";
import {Topic} from "../../entity/Topic";

@Component({
  selector: 'app-topics-page',
  templateUrl: './topics-page.component.html',
  styleUrls: ['./topics-page.component.css']
})
export class TopicsPageComponent {
  loading = false;
  term: string = '';

  topicService: TopicService;
  modalService: ModalService;

  topics: Topic[];

  constructor(topicService: TopicService, modalService: ModalService) {
    this.topicService = topicService;
    this.modalService = modalService;
  }

  ngOnInit() {
    this.loading = true;

    this.topicService.getAll()
      .subscribe(result => {
        this.topics = result;
        this.loading = false;
      });
  }
}
