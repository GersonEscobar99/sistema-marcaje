import { Component } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'marcaje-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  constructor(private loginService: LoginService,
              private router: Router,){}

  onLogout(){
    this.loginService.logout();
    alert('Saliste correctamente.');
    this.router.navigate(['/home']);
  }


}
