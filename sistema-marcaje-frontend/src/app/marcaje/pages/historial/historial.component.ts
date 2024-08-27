// historial.component.ts
import { Component, OnInit } from '@angular/core';
import { MarcajeService } from '../../services/marcaje.service';


@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html'
})
export class HistorialComponent implements OnInit {

  username: string = '';
  marcajes: any[] = [];

  constructor(private marcajeService: MarcajeService) { }

  ngOnInit(): void {
    this.username = localStorage.getItem('username') || '';
    this.obtenerHistorial();
  }

  obtenerHistorial(): void {
    this.marcajeService.obtenerHistorial(this.username).subscribe(
      data => {
        this.marcajes = data;
      },
      error => {
        alert('Hubo un error al obtener el historial.');
      }
    );
  }
}
