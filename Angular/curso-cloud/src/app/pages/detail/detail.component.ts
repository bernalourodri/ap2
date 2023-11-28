import { Dialog } from '@angular/cdk/dialog';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CreateAulaComponent } from 'src/app/create-aula/create-aula.component';
import {AulasModel } from 'src/app/model/aula.model';
import { Curso } from 'src/app/model/curso.model';
import { AulaService } from 'src/app/services/aula.service';
import { CursoService } from 'src/app/services/curso.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  curso?: Curso;
  aula?: AulasModel[];
  showCriarAula = false;

  constructor(private cursoService: CursoService, private aulaService: AulaService ,private route: ActivatedRoute){

  }

  ngOnInit(): void {


    let idCurso = this.route.snapshot.params["idCurso"];
    this.cursoService.getCursoById(idCurso).subscribe(response => {
      this.curso = response;
    });
    this.carregarAulas();

  }

  private carregarAulas(){

    let idCurso = this.route.snapshot.params["idCurso"];
    this.aulaService.getAula(idCurso).subscribe(response => {
      this.aula = response;
    });

  }

  public mostrarCriarAula(){
    this.showCriarAula = true;
  }


  public atualizarAulas(){
    this.carregarAulas();

  }

}
