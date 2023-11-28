import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AulaService } from '../services/aula.service';
import { AulasModel } from '../model/aula.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-aula',
  templateUrl: './create-aula.component.html',
  styleUrls: ['./create-aula.component.css'],
  providers: []
})
export class CreateAulaComponent {

  aula = new FormControl('', [Validators.required]);
  @Output() newAulaEvent = new EventEmitter();
  @Input() idCurso:any = '';



  constructor(private aulaService: AulaService, private snackBar: MatSnackBar){

  }

  public criarNovaAula(){
    if (this.aula.hasError("required")){
      return;
    }

    let aula : AulasModel = {
     nome: this.aula.value as string

    };

    this.aulaService.createAula(this.idCurso, aula).subscribe(response => {
      this.snackBar.open("Aula Criada com Sucesso", "ok");
      this.newAulaEvent.emit();
    });


}

}
