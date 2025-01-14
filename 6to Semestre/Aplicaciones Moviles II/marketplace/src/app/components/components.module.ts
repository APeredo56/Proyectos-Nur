import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryModalComponent } from './modals/category-modal/category-modal.component';
import { IonicModule } from '@ionic/angular';
import { DeleteModalComponent } from './modals/delete-modal/delete-modal.component';
import { ProductInfoModalComponent } from './product-info-modal/product-info-modal.component';
import { SendImageModalComponent } from './modals/send-image-modal/send-image-modal.component';
import { ImageViewModalComponent } from './modals/image-view-modal/image-view-modal.component';



@NgModule({
  declarations: [
    CategoryModalComponent,
    DeleteModalComponent,
    ProductInfoModalComponent,
    SendImageModalComponent,
    ImageViewModalComponent,
  ],
  imports: [
    CommonModule,
    IonicModule,
  ],
  exports: [
    CategoryModalComponent,
    DeleteModalComponent,
    ProductInfoModalComponent,
    SendImageModalComponent,
    ImageViewModalComponent,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class ComponentsModule { }
