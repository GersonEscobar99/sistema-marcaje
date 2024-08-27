import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MarcajeRoutingModule } from './marcaje-routing.module';
import { SharedModule } from '../shared/shared.module';
import { MarcajeComponent } from './pages/marcaje/marcaje.component';
import { FormsModule } from '@angular/forms';
import { ListaUsuariosComponent } from './pages/lista-usuarios/lista-usuarios.component';
import { DetalleUsuarioComponent } from './pages/detalle-usuario/detalle-usuario.component';
import { EntradaComponent } from './pages/entrada/entrada.component';
import { SalidaComponent } from './pages/salida/salida.component';
import { HistorialComponent } from './pages/historial/historial.component';


@NgModule({
  declarations: [
    MarcajeComponent,
    ListaUsuariosComponent,
    DetalleUsuarioComponent,
    EntradaComponent,
    SalidaComponent,
    HistorialComponent
  ],
  imports: [
    CommonModule,
    MarcajeRoutingModule,
    SharedModule,
    FormsModule,
  ]
})
export class MarcajeModule { }
