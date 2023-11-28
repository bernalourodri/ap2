import { Component, OnInit } from '@angular/core';
import { CursoService } from '../services/curso.service';
import { Curso } from '../model/curso.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursoComponent implements OnInit{
  cursos: Curso[] = [];

  constructor(private cursoService: CursoService, private router: Router ) {


  }

  ngOnInit(): void {
    this.cursoService.getCursos().subscribe(response => {
      this.cursos = response;
    });
  }

redirectToDetail(id: any){
  this.router.navigate(["detail", id]);
}

}


