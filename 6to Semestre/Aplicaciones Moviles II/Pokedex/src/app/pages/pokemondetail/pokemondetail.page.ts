import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PokemonDetail } from 'src/app/models/PokemonDetail';
import { PokemonqueryService } from 'src/app/services/pokemonquery.service';

@Component({
  selector: 'app-pokemondetail',
  templateUrl: './pokemondetail.page.html',
  styleUrls: ['./pokemondetail.page.scss'],
})
export class PokemondetailPage implements OnInit {
  selectedView = "about";
  pokemonDetail: PokemonDetail = {
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
      descriptions: [],
      capture_rate: 0,
      growthRate: {name: ""},
      propertyName: {
        name: ""
      },
      gender_rate : 0,
      hatch_counter: 0,
      eggGroups: [],
      dexnumbers: [],
      evolutionChain: {
        id: 0,
        evolutionSpecies: []
      }
    },
    abilities: [],
    stats: [],
  };
  bgColor = "";
  fontColor = ""; 
  baseImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

  constructor(private route: ActivatedRoute ,private api: PokemonqueryService) {
    this.route.params.subscribe(params => {
      this.getPokemonDetail(params['id']);
    });
  }

  ngOnInit() {
  }

  getPokemonDetail(id: string){
    this.api.getPokemonDetail(id).subscribe(result => {
      this.pokemonDetail = result.data.pokemon[0];
      this.bgColor = "bg-" + this.pokemonDetail.typeDetail[0].type.name;
      this.fontColor = "font-" + this.pokemonDetail.typeDetail[0].type.name;
    });
  }

  getBgClass(){
    return this.bgColor;
  }

  getFontClass(){
    return this.fontColor;
  }
  

}
