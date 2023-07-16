import {Component, Input} from '@angular/core';
import {ModalCreateWindowService} from "../../service/modal-create-window.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Topic} from "../../entity/Topic";
import {FormService} from "../../service/form.service";
import {Form} from "../../entity/Form";

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.css']
})
export class CreateFormComponent {
  @Input() topic: Topic;

  private formService: FormService;
  private modalService: ModalCreateWindowService;
  private router: Router;

  showAllErrors = false;
  globalError: string = '';

  private subscription: Subscription;

  form = new FormGroup({
    name: new FormControl<string>('', [
      Validators.minLength(3),
      Validators.maxLength(100),
      Validators.required
    ]),
    shown: new FormControl<boolean>(true)
  });

  constructor(formService: FormService,
              modalService: ModalCreateWindowService,
              router: Router) {
    this.formService = formService;
    this.modalService = modalService;
    this.router = router;
  }

  ngOnDestroy() {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  getTopicName(): string {
    if (!this.topic)
      return '';
    return this.topic.name;
  }

  isGlobalErrorSet(): boolean {
    return this.globalError != '';
  }

  submit() {
    if (this.anyErrorExists())
      return;

    this.addForm();
  }

  private anyErrorExists(): boolean {
    this.resetGlobalError();

    if (this.isAnyErrorInFields()) {
      this.showAllErrors = true;
      return true;
    }
    return false;
  }

  private addForm() {
    let form = this.createFormFromFields();

    this.subscription = this.formService.saveForm(form, this.topic)
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

  private createFormFromFields(): Form {
    let name = this.getNameFromField();
    let shown = this.getShownFromField();

    return new Form(null, name, null, null, shown);
  }

  private getNameFromField(): string {
    return this.form.controls.name.getRawValue();
  }

  private getShownFromField(): boolean {
    return this.form.controls.shown.getRawValue();
  }

  private isAnyErrorInFields(): boolean {
    return this.doesControlHaveError(this.form.controls.name);
  }

  private doesControlHaveError(formControl: FormControl): boolean {
    return formControl.errors != null;
  }
}
