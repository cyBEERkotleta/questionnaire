import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Gender} from "../../entity/Gender";
import {GenderService} from "../../service/gender.service";
import {INamed} from "../../additional/INamed";
import {FormControl, Validators} from "@angular/forms";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-choice-gender',
  templateUrl: './choice-gender.component.html',
  styleUrls: ['./choice-gender.component.css']
})
export class ChoiceGenderComponent implements OnInit, OnDestroy {
  @Input() formElement: FormControl<Gender>;
  @Input() fieldName: string;
  @Input() showError: boolean = false;

  genders: Gender[];
  selectedGender: Gender;

  private genderService: GenderService;

  private subscription: Subscription;

  constructor(genderService: GenderService) {
    this.genderService = genderService;
  }

  ngOnInit() {
    this.subscription = this.genderService.getAll().subscribe(genders => {
      this.genders = genders;
    });
  }

  ngOnDestroy() {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  onChange(item: INamed) {
    this.selectedGender = item as Gender;
    this.formElement.setValue(this.selectedGender);
  }

  isFieldRequired(): boolean {
    return this.formElement.hasValidator(Validators.required);
  }
}
