import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { PokemonDetail, Stat, TypeDetail } from 'src/app/models/PokemonDetail';
import { UtilitiesService } from 'src/app/services/utilities.service';

@Component({
  selector: 'app-viewstats',
  templateUrl: './viewstats.component.html',
  styleUrls: ['./viewstats.component.scss'],
})
export class ViewstatsComponent  implements OnInit {
  @Input() fontClass = "";
  @Input() pokemonName = "";
  @Input() typeDetail: TypeDetail[] = [];
  @Input() stats: Stat[] = [];

  pokemonTypes : string[] = [];
  constructor(public utilities: UtilitiesService) { }

  ngOnInit() {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['typeDetail']) {
      this.pokemonTypes = this.typeDetail.map(x => x.type.name);
    }
  }

  getTotalStats() {
    let total = 0;
    this.stats.forEach(stat => {
      total += stat.base_stat;
    });
    return total;
  }

  getBgClass(type: string){
    return "bg-" + type;
  }


}
