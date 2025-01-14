import { TestBed } from '@angular/core/testing';

import { PokemonqueryService } from './pokemonquery.service';

describe('PokemonService', () => {
  let service: PokemonqueryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PokemonqueryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
