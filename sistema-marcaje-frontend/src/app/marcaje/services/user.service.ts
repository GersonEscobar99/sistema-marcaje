import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = 'http://localhost:8080';

  constructor(private htt: HttpClient) { }

  public addUser(user: User): Observable<any>{
    const url = `${this.apiUrl}/usuarios/`
    return this.htt.post<User>(`${url}`, user);
  }
}
