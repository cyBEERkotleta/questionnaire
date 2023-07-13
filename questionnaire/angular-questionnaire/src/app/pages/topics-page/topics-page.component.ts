import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ModalService} from "../../service/modal.service";
import {TopicService} from "../../service/topic.service";

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

  constructor(topicService: TopicService, modalService: ModalService) {
    this.topicService = topicService;
    this.modalService = modalService;
  }

  ngOnInit() {
    this.loading = true;

    this.topicService.getAll()
      .subscribe(() => {
        this.loading = false;
      });
  }
}
