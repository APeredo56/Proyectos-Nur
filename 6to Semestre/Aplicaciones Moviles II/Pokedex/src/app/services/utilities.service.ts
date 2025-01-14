import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilitiesService {

  constructor() { }

  public pokemonTypes : string[] = ["normal", "fire", "water", "electric", "grass", "ice", "fighting",  "poison", "ground", "flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy"];

  naturalToRoman(num: number): string{
    const romanNumerals = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X'];
    return romanNumerals[num];
  }

  metersToFeetAndInches(meters: number): { feet: number; inches: number } {
    const feetValue = meters * 3.28084;
    let feet = Math.floor(feetValue); 
    const inchesValue = (feetValue - feet) * 12;
    let inches = Math.round(inchesValue);
    if (inches >= 12) {
      const extraFeet = Math.floor(inches / 12);
      feet += extraFeet;
      inches -= extraFeet * 12;
    }
  
    return { feet, inches };
  }

  separateAndCapitalizeText(text: string, currentSeparator: string, newSeparator: string){
    return text.split(currentSeparator).map(x => x.charAt(0).toUpperCase() + x.slice(1)).join(newSeparator);
  }

  defensesDmg: Record<string, Record<string, number>> = {
    "normal": { "fighting": 2, "ghost": 0 },
    "fire": { "fire": 0.5, "water": 2, "grass": 0.5, "ice": 0.5, "ground": 2, "bug": 0.5, "rock": 2, "steel": 0.5, "fairy": 0.5 },
    "water": { "fire": 0.5, "water": 0.5, "grass": 2, "electric": 2, "ice": 0.5, "steel": 0.5 },
    "electric": { "electric": 0.5, "ground": 2, "flying": 0.5, "steel": 0.5 },
    "grass": { "fire": 2, "water": 0.5, "grass": 0.5, "electric": 0.5, "ice": 2, "poison": 2, "ground": 0.5, "flying": 2, "bug": 2 },
    "ice": { "fire": 2, "ice": 0.5, "fighting": 2, "rock": 2, "steel": 2 },
    "fighting": { "flying": 2, "psychic": 2, "bug": 0.5, "rock": 0.5, "dark": 0.5, "fairy": 2 },
    "poison": { "grass": 0.5, "fighting": 0.5, "poison": 0.5, "ground": 2, "psychic": 2, "bug": 0.5, "fairy": 0.5 },
    "ground": { "water": 2, "grass": 2, "electric": 0, "ice": 2, "poison": 0.5, "rock": 0.5 },
    "flying": { "grass": 0.5, "electric": 2, "ice": 2, "fighting": 0.5, "ground": 0, "bug": 0.5, "rock": 2 },
    "psychic": { "fighting": 0.5, "psychic": 0.5, "bug": 2, "ghost": 2, "dark": 2 },
    "bug": { "fire": 2, "grass": 0.5, "fighting": 0.5, "ground": 0.5, "flying": 2, "rock": 2 },
    "rock": { "normal": 0.5, "fire": 0.5, "water": 2, "grass": 2, "fighting": 2, "poison": 0.5, "ground": 2, "flying": 0.5, "steel": 2 },
    "ghost": { "normal": 0, "fighting": 0, "poison": 0.5, "bug": 0.5, "ghost": 2, "dark": 2 },
    "dragon": { "fire": 0.5, "water": 0.5, "grass": 0.5, "electric": 0.5, "ice": 2, "dragon": 2, "fairy": 2 },
    "dark": { "fighting": 2, "psychic": 0, "bug": 2, "ghost": 0.5, "dark": 0.5, "fairy": 2 },
    "steel": { "normal": 0.5, "fire": 2, "grass": 0.5, "ice": 0.5, "fighting": 2, "poison": 0, "ground": 2, "flying": 0.5, "psychic": 0.5, "bug": 0.5, "rock": 0.5, "dragon": 0.5, "steel": 0.5, "fairy": 0.5},
    "fairy": { "fighting": 0.5, "poison": 2, "bug": 0.5, "dragon": 0, "dark": 0.5, "steel": 2 },
  };

  attackDmg: Record<string, Record<string, number>> = {
    "normal": { "rock": 0.5, "ghost": 0, "steel": 0.5 },
    "fire": { "fire": 0.5, "water": 0.5, "grass": 2, "ice": 2, "bug": 2, "rock": 0.5, "dragon": 0.5, "steel": 2 },
    "water": { "fire": 2, "water": 0.5, "grass": 0.5, "ground": 2, "rock": 2, "dragon": 0.5 },
    "electric": { "water": 2, "grass": 2, "electric": 0.5, "ground": 0, "flying": 2, "dragon": 0.5 },
    "grass": { "fire": 0.5, "water": 2, "grass": 0.5, "poison": 0.5, "ground": 2, "flying": 0.5, "bug": 0.5, "rock": 2, "dragon": 0.5, "steel": 0.5 },
    "ice": { "fire": 0.5, "water": 0.5, "grass": 2, "ice": 0.5, "ground": 2, "flying": 2, "dragon": 2, "steel": 0.5 },
    "fighting": { "normal": 2, "ice": 2, "poison": 0.5, "flying": 0.5, "psychic": 0.5, "bug": 0.5, "rock": 2, "ghost": 0, "dark": 2, "steel": 2, "fairy": 0.5 },
    "poison": { "grass": 2, "poison": 0.5, "ground": 0.5, "rock": 0.5, "ghost": 0.5, "steel": 0, "fairy": 2 },
    "ground": { "fire": 2, "electric": 2, "grass": 0.5, "poison": 2, "flying": 0, "bug": 0.5, "rock": 2, "steel": 2 },
    "flying": { "electric": 0.5, "grass": 2, "fighting": 2, "bug": 2, "rock": 0.5, "steel": 0.5 },
    "psychic": { "fighting": 2, "poison": 2, "psychic": 0.5, "dark": 0, "steel": 0.5 },
    "bug": { "fire": 0.5, "grass": 2, "fighting": 0.5, "poison": 0.5, "flying": 0.5, "psychic": 2, "ghost": 0.5, "dark": 2, "steel": 0.5, "fairy": 0.5 },
    "rock": { "fire": 2, "ice": 2, "fighting": 0.5, "ground": 0.5, "flying": 2, "bug": 2, "steel": 0.5 },
    "ghost": { "normal": 0, "psychic": 2, "ghost": 2, "dark": 0.5 },
    "dragon": { "dragon": 2, "steel": 0.5, "fairy": 0 },
    "dark": { "fighting": 0.5, "psychic": 2, "ghost": 2, "dark": 0.5, "fairy": 0.5 },
    "steel": { "fire": 0.5, "water": 0.5, "electric": 0.5, "ice": 2, "rock": 2, "steel": 0.5, "fairy": 2},
    "fairy": { "fighting": 2, "fire": 0.5, "poison": 0.5, "dragon": 2, "dark": 2, "steel": 0.5 },
  };

  typeWeaknesses: Record<string, string[]> = {
    normal: ["fighting"],
    fire: ["water", "rock", "ground"],
    water: ["electric", "grass"],
    electric: ["ground"],
    grass: ["fire", "ice", "poison", "flying", "bug"],
    ice: ["fire", "fighting", "rock", "steel"],
    fighting: ["flying", "psychic", "fairy"],
    poison: ["ground", "psychic"],
    ground: ["water", "ice", "grass"],
    flying: ["electric", "ice", "rock"],
    psychic: ["bug", "ghost", "dark"],
    bug: ["fire", "flying", "rock"],
    rock: ["water", "grass", "fighting", "ground", "steel"],
    ghost: ["ghost", "dark"],
    dragon: ["ice", "dragon", "fairy"],
    dark: ["fighting", "bug", "fairy"],
    steel: ["fire", "fighting", "ground"],
    fairy: ["poison", "steel"],
  };

  typesResistances: Record<string, string[]> = {
    water: ["fire", "steel", "water", "ice"],
    electric: ["electric", "flying", "steel"],
    grass: ["water", "electric", "grass", "ground"],
    ice: ["ice"],
    fighting: ["bug", "rock", "dark"],
    poison: ["fighting", "poison", "grass", "fairy", "bug"],
    ground: ["electric", "poison", "rock"],
    flying: ["grass", "fighting", "bug", "ground"],
    psychic: ["fighting", "psychic"],
    bug: ["grass", "fighting", "ground"],
    rock: ["normal", "fire", "poison", "flying"],
    ghost: ["poison", "bug", "fighting", "normal"],
    dragon: ["electric", "grass", "water", "fire"],
    dark: ["ghost", "dark"],
    steel: ["normal", "grass", "ice", "flying", "psychic", "bug", "rock", "ghost", "dragon", "steel", "fairy", "poison"],
    normal: ["rock", "steel", "ghost"],
    fire: ["fire", "water", "rock", "dragon", "grass", "ice", "bug", "steel", "fairy"],
    fairy: ["fighting", "bug", "dark", "dragon"],
  };
  
  calculateDefense(pokemonTypes: string[], attackerType: string): number {
    let totalDefense = 1;
    for (const defenderType in pokemonTypes) {
      if (attackerType in this.defensesDmg[pokemonTypes[defenderType]]){
        totalDefense *= this.defensesDmg[pokemonTypes[defenderType]][attackerType];
      }
    }
    return totalDefense;
  }

  getWeaknesses(pokemonTypes: string[]): string[] {
    let weaknesses: string[] = [];

    for (const pokemonType of pokemonTypes) {
      if (pokemonType in this.typeWeaknesses) {
        weaknesses.push(...this.typeWeaknesses[pokemonType]);
      }
      if (pokemonType in this.typesResistances) {
        weaknesses = weaknesses.filter((weakness) => !this.typesResistances[pokemonType].includes(weakness));
      }
    }
    return Array.from(new Set(weaknesses));
  }

  getMinStat(baseStat: number) {
    return Math.floor(((baseStat * 2) + 5) * 0.9);  
  }

  getMaxStat(baseStat: number) {
    return Math.floor(((baseStat * 2 + 31 + 63) + 5) * 1.1);  
  }

  getMinHP(baseStat: number) {
    return Math.floor((baseStat * 2) + 110);  
  }

  getMaxHP(baseStat: number) {
    return Math.floor((baseStat * 2 + 31 + 63) + 110);  
  }
  
}


