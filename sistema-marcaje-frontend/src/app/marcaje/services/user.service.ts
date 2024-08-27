import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { map, Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public addUser(user: User): Observable<any>{
    const url = `${this.apiUrl}/usuarios/`
    return this.http.post<User>(`${url}`, user);
  }

  obtenerUsuariosPaginados(page: number, size: number): Observable<any> {
    const url = `${this.apiUrl}/usuarios/paginados/`
    let params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<any>(url, { params });
  }

  obtenerUsuario(username: string): Observable<User>{
    const url = `${this.apiUrl}/usuarios/${username}`;
    return this.http.get<User>(url);
  }
}