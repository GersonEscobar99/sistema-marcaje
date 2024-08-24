import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-marcaje',
  templateUrl: './marcaje.component.html',
  styleUrls: ['./marcaje.component.css']
})
export class MarcajeComponent {
  estado: string = '';
  
  marcaje = {
    horaEntrada: '',
    horaSalida: ''
  };

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    const username = 'usuarioEjemplo'; // Reemplazar por el username del usuario logueado

    this.http.get<string>(`/api/marcaje/estado/${username}`).subscribe(data => {
      this.estado = data;
    });
  }

  registrarMarcaje() {
    const username = 'usuarioEjemplo'; // Reemplazar por el username del usuario logueado

    if (this.estado === 'entrada') {
      this.http.post(`/api/marcaje/entrada/${username}`, {}).subscribe(response => {
        console.log('Entrada registrada');
        this.estado = 'salida';
      });
    } else if (this.estado === 'salida') {
      this.http.post(`/api/marcaje/salida/${username}`, {}).subscribe(response => {
        console.log('Salida registrada');
        this.estado = 'entrada';
      });
    }
  }

  onSubmit() {
    console.log('Marcaje registrado:', this.marcaje);
  }
}
