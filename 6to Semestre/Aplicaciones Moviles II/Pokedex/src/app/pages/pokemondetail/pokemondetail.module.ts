import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PokemondetailPageRoutingModule } from './pokemondetail-routing.module';

import { PokemondetailPage } from './pokemondetail.page';
import { ComponentsModule } from 'src/app/components/components.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PokemondetailPageRoutingModule,
    ComponentsModule
  ],
  declarations: [PokemondetailPage]
})
export class PokemondetailPageModule {}
