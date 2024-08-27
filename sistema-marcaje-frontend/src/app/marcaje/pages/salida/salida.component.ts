import { Component, OnInit } from '@angular/core';
import { MarcajeService } from '../../services/marcaje.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-salida',
  templateUrl: './salida.component.html',
  styleUrls: ['./salida.component.css']
})
export class SalidaComponent implements OnInit {

  username: string = '';
  horaActual: string = '';
  entradaRegistrada: boolean = false;

  constructor(private marcajeService: MarcajeService, private router:Router){}

  ngOnInit(): void {
    this.username = localStorage.getItem('username') || ''; 
    this.actualizarHoraActual();

    this.marcajeService.obtenerUltimoMarcaje(this.username).subscribe(
      (marcaje) => {
        if (marcaje && marcaje.horaSalida) {
          this.router.navigate(['/entrada']);
        }
      },
      error => {
        console.error('Error al verificar el último marcaje', error);
      }
    );
  }

  actualizarHoraActual(): void {
    const now = new Date();
    this.horaActual = now.toLocaleTimeString();
  }

  registrarSalida(): void {
    this.marcajeService.registrarSalida(this.username).subscribe(
      data => {
        alert('Salida registrada exitosamente.');
        this.entradaRegistrada = true;
      },
      error => {
        alert('Hubo un error al registrar la salida.');
      }
    );
  }

}
