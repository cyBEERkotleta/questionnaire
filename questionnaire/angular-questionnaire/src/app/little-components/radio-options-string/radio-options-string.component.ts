import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Field} from "../../entity/Field";
import {FormControl, FormGroup} from "@angular/forms";
import {FieldOption} from "../../entity/FieldOption";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-radio-options-string',
  templateUrl: './radio-options-string.component.html',
  styleUrls: ['./radio-options-string.component.css']
})
export class RadioOptionsStringComponent implements OnInit, OnDestroy {
  @Input() field: Field;
  @Input() required: boolean = false;
  @Input() formElement: FormControl<string>;
  @Input() showError: boolean = false;

  form = new FormGroup({
    fieldOption: new FormControl<FieldOption>(null)
  });

  private subscription: Subscription;

  ngOnInit() {
    this.subscription = this.form.controls.fieldOption.valueChanges
      .subscribe(value => {
        let strValue = value.shownName;
        this.formElement.setValue(strValue);
      });
  }

  ngOnDestroy() {
    if (this.subscription)
      this.subscription.unsubscribe();
  }
}
