import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path:'tabs',
    component: TabsPage,
    children: [
      {
        path:'home', 
        loadChildren: () => import('../home/home.module').then( m => m.HomePageModule)
      },    
      {
        path: 'chats',
        children: [
          {
            path: '',
            loadChildren: () => import('../chats/chats.module').then( m => m.ChatsPageModule)
          },
        ]
      },
      {
        path: 'account',
        children: [
          {
            path: '',
            loadChildren: () => import('../account/account.module').then( m => m.AccountPageModule)
          },
          {
            path: 'addProduct',
            loadChildren: () => import('../product-form/product-form.module').then( m => m.ProductFormPageModule)
          },
          {
            path: 'productList',
            children: [
              {
                path: '',
                loadChildren: () => import('../user-products/user-products.module').then( m => m.UserProductsPageModule)
              },
              {
                path: ':id',
                loadChildren: () => import('../product-form/product-form.module').then( m => m.ProductFormPageModule)              },
            ]
          }
        ]
      },
      {
        path: '',
        redirectTo: 'tabs/home',
        pathMatch: 'full'
      },
    ]
  },
  {
    path: '',
    redirectTo: 'tabs/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TabsPageRoutingModule {}
