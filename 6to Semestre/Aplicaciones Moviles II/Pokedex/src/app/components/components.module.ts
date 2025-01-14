import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PokemoncardComponent } from './pokemoncard/pokemoncard.component';
import { IonicModule } from '@ionic/angular';
import { ModalfilterComponent } from './modalfilter/modalfilter.component';
import { ModalgenerationComponent } from './modalgeneration/modalgeneration.component';
import { ModalsortComponent } from './modalsort/modalsort.component';
import { ViewaboutComponent } from './viewabout/viewabout.component';
import { ViewstatsComponent } from './viewstats/viewstats.component';
import { ViewevolutionComponent } from './viewevolution/viewevolution.component';



@NgModule({
  declarations: [
    PokemoncardComponent,
    ModalfilterComponent,
    ModalgenerationComponent,
    ModalsortComponent,
    ViewaboutComponent,
    ViewstatsComponent,
    ViewevolutionComponent,
  ],
  imports: [
    CommonModule,
    IonicModule
  ],
  exports: [
    PokemoncardComponent,
    ModalfilterComponent,
    ModalgenerationComponent,
    ModalsortComponent,
    ViewaboutComponent,
    ViewstatsComponent,
    ViewevolutionComponent,
    ]
})
export class ComponentsModule { }
