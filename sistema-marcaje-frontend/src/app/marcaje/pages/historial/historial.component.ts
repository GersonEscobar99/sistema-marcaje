// historial.component.ts
import { Component, OnInit } from '@angular/core';
import { MarcajeService } from '../../services/marcaje.service';
import { Marcaje } from '../../interfaces/marcaje.interface';


@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html'
})
export class HistorialComponent implements OnInit {

  username: string = '';
  marcajes: Marcaje[] = [];

  constructor(private marcajeService: MarcajeService) { }

  ngOnInit(): void {
    this.username = localStorage.getItem('username') || '';
    this.cargarMarcajes();
  }

  cargarMarcajes(): void {
    const username = localStorage.getItem('username') || '';
    this.marcajeService.obtenerHistorial(username).subscribe(
      (data: Marcaje[]) => {
        this.marcajes = data.map(marcaje => ({
          ...marcaje,
          fecha: this.convertirFechaHora(marcaje.fecha)
        }));
      },
      error => {
        console.error('Error al cargar el historial de marcajes', error);
      }
    );

  }


  convertirFechaHora(fechaHora: string): string {
    const fecha = new Date(fechaHora);
    return fecha.toLocaleString(); // Puedes usar toLocaleTimeString() si solo quieres la hora
  }
}
