import { Component, Input, OnInit, Output, EventEmitter, SimpleChanges} from '@angular/core';
import { ModalController } from '@ionic/angular';
import { UtilitiesService } from 'src/app/services/utilities.service';

@Component({
  selector: 'app-modalfilter',
  templateUrl: './modalfilter.component.html',
  styleUrls: ['./modalfilter.component.scss'],
})
export class ModalfilterComponent  implements OnInit {
  typesList: string[] = [];
  typeFilter: string[] = [];
  weaknessFilter: string[] = [];
  heightFilter: string[] = [];
  weightFilter: string[] = [];
  minRange: number = 0;
  maxRange: number = 0;
  @Input() initialMaxRange: number = 1000;
  @Output() chosenFilters: EventEmitter<{ 
    typeFilter: string[], 
    weaknessFilter: string[],
    heightFilter: string[],
    weightFilter: string[],
    minRange: number,
    maxRange: number 
  }> = new EventEmitter();

  constructor(private utilities: UtilitiesService, private modalController: ModalController) { }

  ngOnInit() {
    this.typesList = this.utilities.pokemonTypes
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['initialMaxRange'] && !changes['initialMaxRange'].firstChange) {
      this.maxRange = this.initialMaxRange;
    }
  }

  toggleTypeFilter(type:string){
    if(this.typeFilter.includes(type)){
      this.typeFilter = this.typeFilter.filter(item => item !== type);
      return;
    }
    this.typeFilter.push(type);
  }

  toggleWeaknessFilter(type:string){
    if(this.weaknessFilter.includes(type)){
      this.weaknessFilter = this.weaknessFilter.filter(item => item !== type);
      return;
    }
    this.weaknessFilter.push(type);
  }

  toggleHeightFilter(type:string){
    if(this.heightFilter.includes(type)){
      this.heightFilter = this.heightFilter.filter(item => item !== type);
      return;
    }
    this.heightFilter.push(type);
  }

  toggleWeightFilter(type:string){
    if(this.weightFilter.includes(type)){
      this.weightFilter = this.weightFilter.filter(item => item !== type);
      return;
    }
    this.weightFilter.push(type);
  }

  rangeChanged(event:any){
    this.minRange = event.detail.value.lower;
    this.maxRange = event.detail.value.upper;
  }

  resetFilters(){
    this.typeFilter = [];
    this.weaknessFilter = [];
    this.heightFilter = [];
    this.weightFilter = [];
    this.minRange = 0;
    this.maxRange = this.initialMaxRange;
  }

  emitFilters(){
    this.chosenFilters.emit({
      typeFilter: this.typeFilter,
      weaknessFilter: this.weaknessFilter,
      heightFilter: this.heightFilter,
      weightFilter: this.weightFilter,
      minRange: this.minRange,
      maxRange: this.maxRange
    });
  }

  async closeModal() {
    this.emitFilters();
    await this.modalController.dismiss();
  }

}
