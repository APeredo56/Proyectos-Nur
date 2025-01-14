import { Injectable } from '@angular/core';
import { PokemonPreview } from '../models/PokemonPreview';
import { UtilitiesService } from './utilities.service';

@Injectable({
  providedIn: 'root'
})
export class FilterService {
  private lastSortCriterion: string = "smallest";
  private pokemonFilterData : { typeFilter: string[], weaknessFilter: string[],heightFilter: string[],
    weightFilter: string[], minRange: number, maxRange: number 
  } = { typeFilter: [], weaknessFilter: [], heightFilter: [], weightFilter: [], minRange: 0, maxRange: 0 };
  private lastGenerationFilter: string = "";

  constructor(private utilities: UtilitiesService) { }

  setInitialMaxRange(maxRange: number){
    this.pokemonFilterData.maxRange = maxRange;
  }

  private applyPokemonGenerationFilter(pokemonList: PokemonPreview[]): PokemonPreview[]{
    if(this.lastGenerationFilter === ""){
      return pokemonList;
    }
    return pokemonList.filter(pokemon => pokemon.species.generation.name === this.lastGenerationFilter);
  }

  private applyPokemonFilters(pokemonList: PokemonPreview[]): PokemonPreview[]{
    let filteredList = pokemonList.filter(pokemon => {
      const typeMatch = this.pokemonFilterData.typeFilter.length === 0 || 
        pokemon.typeDetail.some(typeDetail => this.pokemonFilterData.typeFilter.includes(typeDetail.type.name));

      const pokemonTypes = pokemon.typeDetail.map(typeDetail => typeDetail.type.name);
      const pokemonWeaknesses = this.utilities.getWeaknesses(pokemonTypes);
      const weaknessMatch = this.pokemonFilterData.weaknessFilter.length === 0 ||
        pokemonWeaknesses.some(weakness => this.pokemonFilterData.weaknessFilter.includes(weakness));

      let pokemonHeight = "";
      if (pokemon.height < 6) {
        pokemonHeight = "short";
      } else if (pokemon.height < 16) {
        pokemonHeight = "medium";
      } else {
        pokemonHeight = "tall";
      }

      const heightMatch = this.pokemonFilterData.heightFilter.length === 0 ||
        this.pokemonFilterData.heightFilter.includes(pokemonHeight);
      
      let pokemonWeight = "";
      if (pokemon.weight < 460) {
        pokemonWeight = "light";
      } else if (pokemon.weight < 1600) {
        pokemonWeight = "normal";
      } else {
        pokemonWeight = "heavy";
      }
      const weightMatch = this.pokemonFilterData.weightFilter.length === 0 ||
        this.pokemonFilterData.weightFilter.includes(pokemonWeight);

      const rangeMatch = pokemon.id >= this.pokemonFilterData.minRange && 
        pokemon.id <= this.pokemonFilterData.maxRange;

      return typeMatch && weaknessMatch && heightMatch && weightMatch && rangeMatch;
    });
    this.applySortCriterion(this.lastSortCriterion, filteredList);
    return filteredList;
  }

  private applySortCriterion(criterion: string, pokemonList: PokemonPreview[]): PokemonPreview[]{
    this.lastSortCriterion = criterion;
    switch(this.lastSortCriterion){
      case "smallest":
        pokemonList.sort((a,b) => a.id - b.id);
        break;
      case "highest":
        pokemonList.sort((a,b) => b.id - a.id);
        break;
      case "A-Z":
        pokemonList.sort((a,b) => a.name.localeCompare(b.name));
        break;
      case "Z-A":
        pokemonList.sort((a,b) => b.name.localeCompare(a.name));
        break;
    }
    return pokemonList;
  }

  setGenerationFilter(generation: string, pokemonList: PokemonPreview[]): PokemonPreview[]{
    if(generation === this.lastGenerationFilter){
      return pokemonList;
    }
    this.lastGenerationFilter = generation;
    let filteredList = this.applyPokemonFilters(pokemonList);
    filteredList = this.applyPokemonGenerationFilter(filteredList);
    return this.applySortCriterion(this.lastSortCriterion, filteredList); 
  }

  setPokemonFilters(data: { 
    typeFilter: string[], 
    weaknessFilter: string[],
    heightFilter: string[],
    weightFilter: string[],
    minRange: number,
    maxRange: number 
  }, pokemonList: PokemonPreview[]): PokemonPreview[]{
    this.pokemonFilterData = data;
    let filteredList = this.applyPokemonGenerationFilter(pokemonList);
    filteredList = this.applyPokemonFilters(filteredList);
    return this.applySortCriterion(this.lastSortCriterion, filteredList);
  }

  setSortCriterion(criterion: string, pokemonList: PokemonPreview[]): PokemonPreview[]{
    if(criterion === this.lastSortCriterion){
      return pokemonList;
    }
    this.lastSortCriterion = criterion;
    return this.applySortCriterion(criterion, pokemonList);
  }

  searchPokemon(searchTerm: string, pokemonList: PokemonPreview[]): PokemonPreview[]{
    const id = parseInt(searchTerm);
    let filteredList = [];
    if(isNaN(id)){
      filteredList = pokemonList.filter(pokemon => pokemon.name.toLowerCase().includes(searchTerm.toLowerCase()));
    } else {
      filteredList = pokemonList.filter(pokemon => pokemon.id === id);
    }
    this.applyPokemonGenerationFilter(filteredList);
    this.applyPokemonFilters(filteredList);
    return this.applySortCriterion(this.lastSortCriterion, filteredList);
  }

}
