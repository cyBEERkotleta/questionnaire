import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {NavigationExtras, Router} from "@angular/router";
import {User} from "../../entity/User";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Gender} from "../../entity/Gender";
import {TopicService} from "../../service/topic.service";
import {Topic} from "../../entity/Topic";
import {ModalService} from "../../service/modal.service";

@Component({
  selector: 'app-create-topic',
  templateUrl: './create-topic.component.html',
  styleUrls: ['./create-topic.component.css']
})
export class CreateTopicComponent {
  private topicService: TopicService;
  private modalService: ModalService;
  private router: Router;

  showAllErrors = false;
  globalError: string = '';

  form = new FormGroup({
    name: new FormControl<string>('', [
      Validators.minLength(3),
      Validators.maxLength(100),
      Validators.required
    ]),
    description: new FormControl<string>('', [
      Validators.maxLength(250)
    ])
  });

  constructor(topicService: TopicService,
              modalService: ModalService,
              router: Router) {
    this.topicService = topicService;
    this.modalService = modalService;
    this.router = router;
  }

  isGlobalErrorSet(): boolean {
    return this.globalError != '';
  }

  submit() {
    if (this.anyErrorExists())
      return;

    this.addTopic();
  }

  private anyErrorExists(): boolean {
    this.resetGlobalError();

    if (this.isAnyErrorInFields()) {
      this.showAllErrors = true;
      return true;
    }
    return false;
  }

  private addTopic() {
    let topic = this.createTopicFromFields();

    this.topicService.saveTopic(topic)
      .subscribe(result => {
        console.log(result);
        if (result.success) {
          this.modalService.close();
        }
        else {
          this.globalError = result.message;
        }
      });
  }

  private resetGlobalError() {
    this.globalError = '';
  }

  private createTopicFromFields(): Topic {
    let name = this.getNameFromField();
    let description = this.getDescriptionFromField();

    return new Topic(null, name, description, null);
  }

  private getNameFromField(): string {
    return this.form.controls.name.getRawValue();
  }

  private getDescriptionFromField(): string {
    return this.form.controls.description.getRawValue();
  }

  private isAnyErrorInFields(): boolean {
    return this.doesControlHaveError(this.form.controls.name) ||
      this.doesControlHaveError(this.form.controls.description);
  }

  private doesControlHaveError(formControl: FormControl): boolean {
    return formControl.errors != null;
  }
}
