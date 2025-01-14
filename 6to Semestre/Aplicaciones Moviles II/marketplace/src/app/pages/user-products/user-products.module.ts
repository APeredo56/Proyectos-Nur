import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserProductsPageRoutingModule } from './user-products-routing.module';

import { UserProductsPage } from './user-products.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    UserProductsPageRoutingModule
  ],
  declarations: [UserProductsPage]
})
export class UserProductsPageModule {}
