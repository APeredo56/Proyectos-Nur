import { Component, Input, OnInit } from '@angular/core';
import { PokemonPreview } from 'src/app/models/PokemonPreview';

@Component({
  selector: 'app-pokemoncard',
  templateUrl: './pokemoncard.component.html',
  styleUrls: ['./pokemoncard.component.scss'],
})
export class PokemoncardComponent  implements OnInit {
  baseImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
  @Input() displayModel: PokemonPreview = {
    id: 0,
    name: "",
    height: 0,
    weight: 0,
    typeDetail: [],
    species: {
      id: 0,
      generation: {
        name: ""
      }
    }
  };
  constructor() { }

  ngOnInit() {
  }

}
