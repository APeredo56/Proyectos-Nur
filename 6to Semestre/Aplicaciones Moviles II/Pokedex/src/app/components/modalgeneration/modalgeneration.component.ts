import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UtilitiesService } from 'src/app/services/utilities.service';

@Component({
  selector: 'app-modalgeneration',
  templateUrl: './modalgeneration.component.html',
  styleUrls: ['./modalgeneration.component.scss'],
})
export class ModalgenerationComponent  implements OnInit {
  baseUrl: string = "assets/img/gen/Generation ";
  activeBtn: number = 0;
  btnAmount = [1,2,3,4,5,6,7,8]
  @Output() chosenGeneration = new EventEmitter();
  
  constructor(private utilities: UtilitiesService) { }

  ngOnInit() {
  }

  setActiveBtn(num: number) {
    if(num === this.activeBtn) {
      this.activeBtn = 0;
      return;
    }
    this.activeBtn = num;
  }

  getGenRoman = this.utilities.naturalToRoman;

  emitGeneration(){
    let generationString = "";
    if(this.activeBtn !== 0){
      generationString = "generation-" + this.getGenRoman(this.activeBtn).toLowerCase();
    }
    this.chosenGeneration.emit(generationString);
  }

}
