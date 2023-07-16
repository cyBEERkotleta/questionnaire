import {Component, Input} from '@angular/core';
import {Form} from "../../entity/Form";
import {RandomService} from "../../service/random.service";
import {Params} from "@angular/router";

@Component({
  selector: 'app-form-item-in-list',
  templateUrl: './form-item-in-list.component.html',
  styleUrls: ['./form-item-in-list.component.css']
})
export class FormItemInListComponent {
  @Input() form: Form;

  private randomService: RandomService;
  /*private colorsFrom: string[] = ['#f7cbf8', '#befcc8', '#cdc1ff', '#f7ff9f', '#ff3b00', '#388ad6', '#b81634'];
  private colorsTo: string[] = ['#b84be0', '#1ebd8d', '#393fec', '#eab814', '#0048ff', '#a1f7f7', '#ffbdf8'];
  private colorsBorder: string[] = ['#850058', '#046c7a', '#1d0b96', '#db3300', '#4f008c', '#1644b8', '#7d3bbf'];*/
  private colorsFrom: string[] = ['#f2a8f5', '#86e796', '#a190e8', '#fcffe0', '#ffcfd7', '#cff0ff', '#44aeeb',
    '#a3e041', '#ffe0fc', '#c045ed', '#f05d5d', '#d1fff4'];
  private colorsTo: string[] = ['#faf3ff', '#eafff7', '#e0e0ff', '#fcc92e', '#d94a4a', '#5485e8', '#b3faff',
    '#fff7cf', '#45ceed', '#cff6ff', '#feffc7', '#5b78e3'];

  constructor(randomService: RandomService) {
    this.randomService = randomService;
  }

  getFormName(): string {
    if (!this.form)
      return '';
    return this.form.name;
  }

  getFormQuestionsDescription(): string {
    if (!this.form)
      return '';
    return '(' + this.form.fields.length + ' вопросов)';
  }

  private getRandomColorIndex(): number {
    let min = 0;
    let max = this.colorsFrom.length - 1;
    return this.randomService.getRandomNumber(min, max);
  }

  private getBackgroundProperty(index: number): string {
    let colorFrom = this.colorsFrom[index];
    let colorTo = this.colorsTo[index];
    return 'linear-gradient(to top left, ' + colorFrom + ', ' + colorTo +')';
  }

  /*private getBorderProperty(index: number): string {
    return '2px ' + this.colorsBorder[index] + ' solid';
  }*/

  getProperties(): Params {
    let index = this.getRandomColorIndex();
    return {background: this.getBackgroundProperty(index)/*, border: this.getBorderProperty(index)*/};
  }
}
