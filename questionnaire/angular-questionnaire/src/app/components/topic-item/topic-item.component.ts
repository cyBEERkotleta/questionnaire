import {Component, Input} from '@angular/core';
import {Topic} from "../../entity/Topic";
import {NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'app-topic-item',
  templateUrl: './topic-item.component.html',
  styleUrls: ['./topic-item.component.css']
})
export class TopicItemComponent {
  @Input() topic: Topic;

  private router: Router;

  constructor(router: Router) {
    this.router = router;
  }

  doTransferToFormsOfTopicPage() {
    let navigationExtras: NavigationExtras = {
      queryParams: {'topic_id': this.topic.id}
    };

    this.router.navigate(['/forms-by-topic'], navigationExtras);
  }
}
