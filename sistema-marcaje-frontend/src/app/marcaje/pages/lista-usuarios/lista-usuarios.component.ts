import { Component, OnInit } from '@angular/core';
import { User } from '../../interfaces/user.interface';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css']
})
export class ListaUsuariosComponent implements OnInit {
  usuarios:User [] = [];
  currentPage: number = 0;
  totalPages!: number;

  constructor(private usuarioService: UserService) {}

  ngOnInit() {
    this.cargarUsuarios(this.currentPage);
  }

  cargarUsuarios(page: number) {
    this.usuarioService.obtenerUsuariosPaginados(page, 10).subscribe(data => {
      this.usuarios = data.content;
      this.totalPages = data.totalPages;
    });
  }

  siguientePagina() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.cargarUsuarios(this.currentPage);
    }
  }

  paginaAnterior() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.cargarUsuarios(this.currentPage);
    }
  }
}
