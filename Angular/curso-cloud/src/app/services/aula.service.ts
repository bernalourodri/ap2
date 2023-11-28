import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AulasModel } from '../model/aula.model';

@Injectable({
  providedIn: 'root'
})
export class AulaService {

  constructor(private http: HttpClient) { }

    public getAula(idCurso:any) : Observable<AulasModel[]>{
      return this.http.get<AulasModel[]>(`https://ap1-ibmec.azurewebsites.net/curso/${idCurso}/aula`);

    }

    public createAula(idCurso: any, aula: AulasModel):Observable<AulasModel>{
      return this.http.post<AulasModel>(`https://ap1-ibmec.azurewebsites.net/curso/${idCurso}/aula`, aula)
    }
}
