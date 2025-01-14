import { Injectable } from '@angular/core';
import { Apollo, gql } from 'apollo-angular';
import { PokemonPreview } from '../models/PokemonPreview';
import { PokemonDetail } from '../models/PokemonDetail';

const pokemonListQuery = gql`query pokemonPreviewQuery {
  pokemon: pokemon_v2_pokemon(where: {id: {_lt: 10264}}) {
    id
    name
    height
    weight
    typeDetail: pokemon_v2_pokemontypes {
      type: pokemon_v2_type {
        name
      }
    }
    species: pokemon_v2_pokemonspecy {
      id
      generation: pokemon_v2_generation {
        name
      }
    }
  }
}`;

@Injectable({
  providedIn: 'root'
})
export class PokemonqueryService {
  

  constructor(private apollo: Apollo) { }

  public getPokemonPreviews() {
    return this.apollo.query<{pokemon: PokemonPreview[]}>({
      query: pokemonListQuery
    });
  }

  public getPokemonDetail(id: string){
    const pokemonDetailQuery = gql`query pokemonDetailQuery {
      pokemon: pokemon_v2_pokemon(where: {id: {_eq: ${id}}}) {
        id
        name
        typeDetail: pokemon_v2_pokemontypes {
          type: pokemon_v2_type {
            name
          }
        }
        weight
        height
        base_experience
        species: pokemon_v2_pokemonspecy {
          id
          speciesName: pokemon_v2_pokemonspeciesnames(where: {language_id: {_eq: 9}}) {

            name: genus
          }
          base_happiness
          descriptions: pokemon_v2_pokemonspeciesflavortexts(where: {language_id: {_eq: 9}}) {
            description: flavor_text
          }
          capture_rate
          growthRate: pokemon_v2_growthrate {
            name
          }
          gender_rate
          hatch_counter
          eggGroups: pokemon_v2_pokemonegggroups {
            eggGroup: pokemon_v2_egggroup {
              name
            }
          }
          dexnumbers: pokemon_v2_pokemondexnumbers {
            pokedex_number
            pokedex: pokemon_v2_pokedex {
              version_group: pokemon_v2_pokedexversiongroups {
                group: pokemon_v2_versiongroup {
                  name
                }
              }
              region: pokemon_v2_region {
                name
              }
            }
          }
        evolutionChain: pokemon_v2_evolutionchain {
            id
            evolutionSpecies: pokemon_v2_pokemonspecies {
              pokemonId: id
              name
              evolveFromId: evolves_from_species_id
              evolution: pokemon_v2_pokemonevolutions {
                move: pokemon_v2_move {
                  name
                }
                time_of_day
                turn_upside_down
                min_affection
                min_beauty
                min_happiness
                min_level
                needs_overworld_rain
                heldItem: pokemonV2ItemByHeldItemId {
                  name
                }
                item: pokemon_v2_item {
                  name
                }
                trigger: pokemon_v2_evolutiontrigger {
                  name
                }
              }
            }
          }
        }
        abilities: pokemon_v2_pokemonabilities {
          ability: pokemon_v2_ability {
            name
          }
          is_hidden
        }
        stats: pokemon_v2_pokemonstats {
          effort
          base_stat
          stat: pokemon_v2_stat {
            name
          }
        }
      }
    }
    `;

    return this.apollo.query<{pokemon: PokemonDetail[]}>({
      query: pokemonDetailQuery
    });
  }
}
