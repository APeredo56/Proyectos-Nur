import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'pokemon',
    loadChildren: () => import('./pages/pokemondetail/pokemondetail.module').then( m => m.PokemondetailPageModule)
  },
  {
    path: 'pokemon/:id',
    loadChildren: () => import('./pages/pokemondetail/pokemondetail.module').then( m => m.PokemondetailPageModule)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
