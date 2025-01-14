import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Evolution, EvolutionSpecy, PokemonDetail } from 'src/app/models/PokemonDetail';
import { UtilitiesService } from 'src/app/services/utilities.service';

@Component({
  selector: 'app-viewevolution',
  templateUrl: './viewevolution.component.html',
  styleUrls: ['./viewevolution.component.scss'],
})
export class ViewevolutionComponent  implements OnInit {
  @Input() fontClass = "";
  @Input() evolutionDetails: EvolutionSpecy[] = [];

  baseImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

  constructor(private utilities: UtilitiesService) { }

  ngOnInit() {}

  pokemonMap = new Map<number, any>();

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['evolutionDetails']) {
      let tempArray = [...this.evolutionDetails];
      tempArray.sort((a, b) => {
        if (a.evolveFromId === null) return -1; // Si a no evoluciona desde otro, a debe ir antes
        if (b.evolveFromId === null) return 1;  // Si b no evoluciona desde otro, b debe ir antes
        return a.evolveFromId - b.evolveFromId; // Ordenar por evolveFromId
      });
      this.evolutionDetails = tempArray;
    }    
  }

  getEvolutionTrigger(evolutionConditions: Evolution){
    if (this.evolutionDetails.length === 0) return "";
    switch(evolutionConditions.trigger.name){
      case "level-up":
        if (evolutionConditions.min_level !== null) {
          return `Level ${evolutionConditions.min_level}`;
        } else if (evolutionConditions.min_happiness !== null) {
          return `Happiness ${evolutionConditions.min_happiness}`;
        } else if (evolutionConditions.min_beauty !== null) {
          return `Beauty ${evolutionConditions.min_beauty}`;
        } else if (evolutionConditions.min_affection !== null) {
          return `Affection ${evolutionConditions.min_affection}`;
        } else if (evolutionConditions.needs_overworld_rain) {
          return "Rain";
        } else if (evolutionConditions.time_of_day !== "") {
          return this.utilities.separateAndCapitalizeText(evolutionConditions.time_of_day, "-", " ");
        } else if (evolutionConditions.turn_upside_down) {
          return "Upside Down";
        }
        return "Level Up";
      case "trade":
        return "Trade";
      case "use-item":
        return this.utilities.separateAndCapitalizeText(evolutionConditions.item.name, "-", " ")
      default:
        return this.utilities.separateAndCapitalizeText(evolutionConditions.trigger.name, "-", " ");
  }
  }

  getPreviusEvolution(evolution: EvolutionSpecy){
    const previusEvolution = this.evolutionDetails.find(x => x.pokemonId === evolution.evolveFromId);
    return previusEvolution === undefined ? evolution : previusEvolution;
  }
}
