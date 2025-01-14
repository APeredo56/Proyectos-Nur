import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PokemondetailPage } from './pokemondetail.page';

const routes: Routes = [
  {
    path: '',
    component: PokemondetailPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PokemondetailPageRoutingModule {}
