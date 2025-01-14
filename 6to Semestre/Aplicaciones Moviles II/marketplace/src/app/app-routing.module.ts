import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./pages/tabs/tabs.module').then( m => m.TabsPageModule)
  },
  {
    path: 'auth',
    loadChildren: () => import('./pages/auth/auth.module').then( m => m.AuthPageModule)
  },
  {
    path: 'product/:id',
    loadChildren: () => import('./pages/product-detail/product-detail.module').then( m => m.ProductDetailPageModule)
  },
  {
    path: 'chats/:id',
    loadChildren: () => import('./pages/chat-detail/chat-detail.module').then( m => m.ChatDetailPageModule)
  },
  {
    path: 'product/:id/chats',
    loadChildren: () => import('./pages/chats/chats.module').then( m => m.ChatsPageModule)
  },
  {
    path: 'selectLocation',
    loadChildren: () => import('./pages/select-location/select-location.module').then( m => m.SelectLocationPageModule)
  },
  {
    path: 'sendLocation',
    loadChildren: () => import('./pages/send-location/send-location.module').then( m => m.SendLocationPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
