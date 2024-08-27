import { Component, OnInit } from '@angular/core';
import { MarcajeService } from '../../services/marcaje.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html',
  styleUrls: ['./entrada.component.css']
})
export class EntradaComponent implements OnInit {

  username: string = '';
  horaActual: string = '';
  entradaRegistrada: boolean = false;

  constructor(private marcajeService:MarcajeService, private router: Router){}

  ngOnInit(): void {
    this.username = localStorage.getItem('username') || ''; 
    this.actualizarHoraActual();

    this.marcajeService.obtenerUltimoMarcaje(this.username).subscribe(
      (marcaje) => {
        if (marcaje && !marcaje.horaSalida) {
          this.router.navigate(['/salida']);
        } else if(marcaje && marcaje.horaEntrada){
          this.entradaRegistrada= true;
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
  
  
  registrarEntrada(): void {
    this.marcajeService.registrarEntrada(this.username).subscribe(
      data => {
        alert('Entrada registrada exitosamente.');
        this.entradaRegistrada = true;
      
      },
      error => {
        alert('Hubo un error al registrar la entrada.');
      }
    );
  }
  
}
