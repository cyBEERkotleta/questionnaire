import {Answer} from "./Answer";

export class AnsweredForm {
  id: bigint;
  answers: Answer[];

  constructor(id: bigint, answers: Answer[]) {
    this.id = id;
    this.answers = answers;
  }
}
