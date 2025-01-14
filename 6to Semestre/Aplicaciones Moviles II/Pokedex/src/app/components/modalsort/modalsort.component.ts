import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modalsort',
  templateUrl: './modalsort.component.html',
  styleUrls: ['./modalsort.component.scss'],
})
export class ModalsortComponent  implements OnInit {
  @Output() chosenCriterion = new EventEmitter();
  sortCriterion: string = "";

  constructor() { }

  ngOnInit() {}

  setSortCriterion(criterion: string){
    if(this.sortCriterion === criterion){
      this.sortCriterion = "";
      return;
    }
    this.sortCriterion = criterion;
  }

  emitSortCriterion(){
    this.chosenCriterion.emit(this.sortCriterion);
  }
}
