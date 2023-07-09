import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private visible$ = new BehaviorSubject<boolean>(true);

  constructor() { }

  open() {
    this.visible$.next(true);
  }

  close() {
    this.visible$.next(false);
  }

  isVisible(): boolean {
    return this.visible$.getValue();
  }
}
