import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-logotype-text',
  templateUrl: './logotype-text.component.html',
  styleUrls: ['./logotype-text.component.css']
})
export class LogotypeTextComponent {
  @Input() centered: boolean = false;
}
