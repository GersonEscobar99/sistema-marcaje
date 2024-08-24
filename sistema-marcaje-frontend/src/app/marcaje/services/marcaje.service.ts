import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Marcaje } from '../interfaces/marcaje.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarcajeService {

  private apiUrl = 'http://localhost:8080'

  constructor(private htt : HttpClient) { }

  registrerMarking(marking: Marcaje):Observable<Marcaje>{
    const url = `${this.apiUrl}/marcajes/`;
    return this.htt.post<Marcaje>(url,marking);
  }

  getMarkingByuser(userId: number):Observable<Marcaje>{
    const url = `${this.apiUrl}/usuarios/ ${userId}`;
    return this.htt.get<Marcaje>(url);
  }
  
}
