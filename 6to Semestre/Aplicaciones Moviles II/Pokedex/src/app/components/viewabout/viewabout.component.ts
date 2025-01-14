import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { isEmpty } from 'rxjs';
import { PokemonDetail, VersionGroup } from 'src/app/models/PokemonDetail';
import { UtilitiesService } from 'src/app/services/utilities.service';

@Component({
  selector: 'app-viewabout',
  templateUrl: './viewabout.component.html',
  styleUrls: ['./viewabout.component.scss'],
})
export class ViewaboutComponent  implements OnInit {
  @Input() fontClass = "";
  @Input() pokemonDetail: PokemonDetail = {
    id: 0,
    name: "",
    typeDetail: [],
    weight: 0,
    height: 0,
    base_experience: 0,
    species: {
      speciesName: [{name: ""}],
      base_happiness: 0,
      id: 0,
      descriptions: [{description: ""}],
      capture_rate: 0,
      growthRate: {name: ""},
      propertyName: {
        name: ""
      },
      gender_rate : 0,
      hatch_counter: 0,
      eggGroups: [{eggGroup: {name: ""}}],
      dexnumbers: [{pokedex_number: 0, pokedex: {version_group: [{group: {name: ""}}], region: {name: ""}}}],
      evolutionChain: {
        id: 0,
        evolutionSpecies: []
      }
    },
    abilities: [{ability: {name: ""}, is_hidden: false}],
    stats: [{effort: 0, base_stat: 0, stat: {name: ""}}],
  };
  genderRatio = {male: 0, female: 0}

  constructor(private utilities: UtilitiesService) { 
  }

  ngOnInit() {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['pokemonDetail']) {
      this.genderRatio = this.calculateGenderRatio(this.pokemonDetail.species.gender_rate);
    }
  }

  getFormattedText(text: string, currentSeparator: string, newSeparator: string){
    return this.utilities.separateAndCapitalizeText(text, currentSeparator, newSeparator);
  }

  getDescription(){
    if(this.pokemonDetail.species.descriptions === undefined || 
      this.pokemonDetail.species.descriptions.length === 0){
      return "";
    }
    let name = this.pokemonDetail.name.split("-")[0];
    name = name.charAt(0).toUpperCase() + name.slice(1);
    let description = this.pokemonDetail.species.descriptions.filter(x => x.description.includes(name));
    if (description.length === 0){
      description = [...this.pokemonDetail.species.descriptions]
    }
    description.sort((a,b) => b.description.length - a.description.length);
    return description[0].description;
  }

  getHeightInFeet(meters: number){
    const conversion = this.utilities.metersToFeetAndInches(meters);
    return conversion.feet + "'" + (conversion.inches + "").padStart(2, "0") + '"';
  }

  getWeaknesses(){
    const types: string[] = [];
    this.pokemonDetail.typeDetail.forEach(type => {
      types.push(type.type.name);
    });
    return this.utilities.getWeaknesses(types);
  }

  getBgClass(type: string){
    return "bg-" + type;
  }

  calculateCatchProbability(){
    let hp = 0;
    for(let stat in this.pokemonDetail.stats){
      if(this.pokemonDetail.stats[stat].stat.name === "hp"){
        hp = this.pokemonDetail.stats[stat].base_stat;
      }
    }
    let calculation = Math.round(((1+hp)*this.pokemonDetail.species.capture_rate)/(hp*3))/256;
    return (calculation * 100).toFixed(1);
  }

  calculateGenderRatio(genderRate: number): {male: number; female:number}{
    const male = (8 - genderRate) * 100 / 8;
    const female = genderRate * 100 / 8;
    return {male, female};
  }

  getEggGroups(){
    const eggGroups: string[] = [];
    this.pokemonDetail.species.eggGroups.forEach(eggGroup => {
      eggGroups.push(eggGroup.eggGroup.name.charAt(0).toUpperCase() + eggGroup.eggGroup.name.slice(1));
    });
    return eggGroups.join(", ");
  }

  getEggCycles(){
    const minSteps = (this.pokemonDetail.species.hatch_counter * 244.2).toLocaleString('en-US');
    const maxSteps = (this.pokemonDetail.species.hatch_counter * 257).toLocaleString('en-US');
    return minSteps + " - " + maxSteps;
  }

  getVersionGroups(groups: VersionGroup[]){
    const versions: string[] = [];
    groups.forEach(group => {
      versions.push(group.group.name);
    });
    return this.getFormattedText(versions.join("-"), "-", "/");
  }

}


