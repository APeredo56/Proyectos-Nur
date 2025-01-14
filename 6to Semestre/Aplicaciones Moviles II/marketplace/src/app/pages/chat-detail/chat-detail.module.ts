import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ChatDetailPageRoutingModule } from './chat-detail-routing.module';

import { ChatDetailPage } from './chat-detail.page';
import { ComponentsModule } from 'src/app/components/components.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ChatDetailPageRoutingModule,
    ComponentsModule,
  ],
  declarations: [ChatDetailPage]
})
export class ChatDetailPageModule {}
