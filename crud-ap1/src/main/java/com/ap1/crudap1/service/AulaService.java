package com.ap1.crudap1.service;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap1.crudap1.exception.AulaException;
import com.ap1.crudap1.exception.BusinessException;
import com.ap1.crudap1.model.Aulas;
import com.ap1.crudap1.model.Curso;
import com.ap1.crudap1.repository.AulasRepository;

@Service
public class AulaService {

    @Autowired
    AulasRepository repository;
    
    @Autowired 
    CursoService cursoService;

    public java.util.List<Aulas> findAll() {
        return this.repository.findAll();
    }

    public Optional<Aulas> findById(long id) {
        return this.repository.findById(id);
    }

    public Aulas update(long id, Aulas newData) throws AulaException{
       Optional<Aulas> opAulas = this.repository.findById(id);
        
        if (opAulas.isPresent() == false){
            throw new AulaException("Aula nao encontrado");
        }
        Aulas aulas = opAulas.get();
        aulas.setNome(newData.getNome());

        this.repository.save(aulas);

        return aulas;
    }

    public Aulas save(long idCurso, Aulas item) throws AulaException {
        Optional<Curso> opCurso = this.cursoService.getById(idCurso);

        if(opCurso.isPresent() == false)
            throw new AulaException("Curso nao encontrado");
        
        Curso curso = opCurso.get();

        curso.addAulas(item);
        
        this.cursoService.saveOrUpdate(curso);
        
        return item;
    }

    public void delete(long id) throws AulaException{
        Optional<Aulas> opCurso = this.repository.findById(id);

        if (opCurso.isPresent() == false){
            throw new AulaException("Nao encontrei o curso a ser excluido");
        }

        this.repository.delete(opCurso.get());


    }
    
}
