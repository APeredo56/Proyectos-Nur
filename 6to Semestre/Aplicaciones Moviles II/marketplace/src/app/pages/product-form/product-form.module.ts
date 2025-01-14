import { CUSTOM_ELEMENTS_SCHEMA, Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProductFormPageRoutingModule } from './product-form-routing.module';

import { ProductFormPage } from './product-form.page';
import { ComponentsModule } from 'src/app/components/components.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ProductFormPageRoutingModule,
    ReactiveFormsModule,
    ComponentsModule,
  ],
  declarations: [ProductFormPage],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProductFormPageModule {}
