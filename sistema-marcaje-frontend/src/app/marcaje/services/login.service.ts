import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../interfaces/login.interface';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  private apiUrl = 'http://localhost:8080';
  private isAuthenticated = false;

  constructor(private http: HttpClient) { }

  login(username: string, password: string):Observable<Login>{
    const url = `${this.apiUrl}/auth/login`;
    this.isAuthenticated = true;
    return this.http.post<Login>(url,{username, password});
  }

  logout(){
    this.isAuthenticated = false;
  }

  isUserAuthenticated():boolean{
    return this.isAuthenticated;
  }
}
