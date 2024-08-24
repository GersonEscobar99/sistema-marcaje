import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/marcaje/services/login.service';

@Component({
  selector: 'shared-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginData = {
    username: '',
    password: ''
  };

  constructor(private loginService: LoginService,
              private router:Router
  ){}

  alertMessage: string | null = null;
  alertClass: string = '';

  formSubmit(){
    this.loginService.login(this.loginData.username, this.loginData.password).subscribe(
      (response) => {
        this.showAlert('Inicio de sesión exitoso. ¡Bienvenido!', 'alert-success');
        setTimeout(() => {
          this.router.navigate(['/marcaje']);
        }, 500);
      },
      (error) => {
        this.showAlert('Nombre de usuario o contraseña incorrectos.', 'alert-danger');
        this.loginData.username ='';
        this.loginData.password='';
      }
    );
  }


  showAlert(message: string, alertClass: string) {
    this.alertMessage = message;
    this.alertClass = alertClass;
    setTimeout(() => {
      this.alertMessage = null;
    }, 1000);
  }

  
}
