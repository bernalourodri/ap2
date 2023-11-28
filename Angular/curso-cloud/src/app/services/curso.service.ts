import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Curso } from '../model/curso.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  constructor(private httpClient: HttpClient) { }

  public getCursos() : Observable<Curso[]>{
    return this.httpClient.get<Curso[]>("https://ap1-ibmec.azurewebsites.net/curso");
  }

  public getCursoById(id: any): Observable<Curso>{
    return this.httpClient.get<Curso>("https://ap1-ibmec.azurewebsites.net/curso/" + id);
  }

}
