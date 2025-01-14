import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PokemondetailPage } from './pokemondetail.page';

describe('PokemondetailPage', () => {
  let component: PokemondetailPage;
  let fixture: ComponentFixture<PokemondetailPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(PokemondetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
