import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PokemonPreview } from 'src/app/models/PokemonPreview';
import { FilterService } from 'src/app/services/filter.service';
import { PokemonqueryService } from 'src/app/services/pokemonquery.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  pokemonPreviews: PokemonPreview[] = [];
  filteredPokemonPreviews: PokemonPreview[] = [];
  filterApproved: boolean = true;
  lastSortCriterion: string = "smallest";

  constructor(api: PokemonqueryService, private filter: FilterService, private router: Router) {
    api.getPokemonPreviews().subscribe(result => {
      this.pokemonPreviews = result.data.pokemon;
      this.filteredPokemonPreviews = this.pokemonPreviews;
      filter.setInitialMaxRange(this.filteredPokemonPreviews.length);
    });
  }

  trackByFn(index: number, item: PokemonPreview): number {
    return item.id;
  }

  setGenerationFilter(generation: string){
    this.filteredPokemonPreviews = this.filter.setGenerationFilter(generation, [...this.pokemonPreviews]);
  }

  setPokemonFilters(data: { 
    typeFilter: string[], 
    weaknessFilter: string[],
    heightFilter: string[],
    weightFilter: string[],
    minRange: number,
    maxRange: number 
    }){
    this.filteredPokemonPreviews = this.filter.setPokemonFilters(data, [...this.pokemonPreviews]);
  }

  setSortCriterion(criterion: string){
    this.filteredPokemonPreviews = this.filter.setSortCriterion(criterion, [...this.filteredPokemonPreviews]);
  }

  searchPokemon(event: any){
    const searchTerm = event.target.value.toLowerCase();
    this.filteredPokemonPreviews = this.filter.searchPokemon(searchTerm, [...this.pokemonPreviews]);
  }

  goToPokemonDetail(id: number){
    this.router.navigateByUrl("/pokemon/" + id);
  }

}