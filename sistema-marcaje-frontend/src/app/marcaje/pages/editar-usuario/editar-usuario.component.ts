import { Component, OnInit } from '@angular/core';
import { User } from '../../interfaces/user.interface';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-usuario',
  templateUrl: './editar-usuario.component.html',
  styleUrls: ['./editar-usuario.component.css']
})
export class EditarUsuarioComponent implements OnInit{

  usuario: User | null = null;
  isLoading = true;
  errorMessage: string | null = null;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const usuarioId = Number(params.get('usuarioId'));
      if (usuarioId) {
        this.cargarUsuario(usuarioId);
      }
    });
  }

  cargarUsuario(usuarioId: number): void {
    this.isLoading = true;
    this.userService.obtenerUsuario(usuarioId.toString()).subscribe(usuario => {
      this.usuario = usuario;
      this.isLoading = false;
    }, error => {
      console.error('Error al cargar el usuario', error);
      this.isLoading = false;
      this.errorMessage = 'No se pudo cargar el usuario.';
    });
  }

  actualizarUsuario(): void {
    if (this.usuario) {
      this.isLoading = true;
      this.userService.actualizarUsuario(this.usuario.id!, this.usuario).subscribe(() => {
        this.isLoading = false;
        this.router.navigate(['/user/listaUsuarios']); 
      }, error => {
        console.error('Error al actualizar el usuario', error);
        this.isLoading = false;
        this.errorMessage = 'No se pudo actualizar el usuario.';
      });
    }
  }
}
