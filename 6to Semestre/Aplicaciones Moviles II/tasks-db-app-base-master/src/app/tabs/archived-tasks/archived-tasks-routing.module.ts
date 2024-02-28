import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ArchivedTasksPage } from './archived-tasks.page';

const routes: Routes = [
  {
    path: '',
    component: ArchivedTasksPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ArchivedTasksPageRoutingModule {}
