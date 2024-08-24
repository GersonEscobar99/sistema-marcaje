import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MarcajeComponent } from './pages/marcaje/marcaje.component';

const routes: Routes = [
  {
    path:'marcaje',
    component: MarcajeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MarcajeRoutingModule { }
