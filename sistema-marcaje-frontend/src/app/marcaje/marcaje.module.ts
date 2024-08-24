import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MarcajeRoutingModule } from './marcaje-routing.module';
import { SharedModule } from '../shared/shared.module';
import { MarcajeComponent } from './pages/marcaje/marcaje.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    MarcajeComponent
  ],
  imports: [
    CommonModule,
    MarcajeRoutingModule,
    SharedModule,
    FormsModule,
  ]
})
export class MarcajeModule { }
