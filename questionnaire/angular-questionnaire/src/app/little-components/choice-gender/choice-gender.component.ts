import {Component, Input, OnInit} from '@angular/core';
import {Gender} from "../../entity/Gender";
import {GenderService} from "../../service/gender.service";
import {INamed} from "../../additional/INamed";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-choice-gender',
  templateUrl: './choice-gender.component.html',
  styleUrls: ['./choice-gender.component.css']
})
export class ChoiceGenderComponent implements OnInit {
  @Input() formElement: FormControl<Gender>;
  @Input() fieldName: string;
  @Input() showError: boolean = false;

  genders: Gender[];
  selectedGender: Gender;

  private genderService: GenderService;

  constructor(genderService: GenderService) {
    this.genderService = genderService;
  }

  ngOnInit() {
    this.genderService.getAll().subscribe(genders => {
      this.genders = genders;
    });
  }

  onChange(item: INamed) {
    this.selectedGender = item as Gender;
    this.formElement.setValue(this.selectedGender);
  }

  isFieldRequired(): boolean {
    return this.formElement.hasValidator(Validators.required);
  }
}
