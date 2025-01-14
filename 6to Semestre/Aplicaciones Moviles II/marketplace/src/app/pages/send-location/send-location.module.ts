import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { SendLocationPageRoutingModule } from './send-location-routing.module';

import { SendLocationPage } from './send-location.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    SendLocationPageRoutingModule
  ],
  declarations: [SendLocationPage],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class SendLocationPageModule {}
