package com.ap1.crudap1.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap1.crudap1.exception.AulaException;
import com.ap1.crudap1.exception.BusinessException;
import com.ap1.crudap1.model.Aulas;
import com.ap1.crudap1.model.Curso;
import com.ap1.crudap1.service.AulaService;
import com.ap1.crudap1.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso/{idCurso}/aula")
@CrossOrigin
class resourceNameController {

    @Autowired
    AulaService aulaService;

    @Autowired
    CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Aulas>> getAll(@PathVariable("idCurso") long idCurso) {
        try {
            Optional<Curso> opCurso = this.cursoService.getById(idCurso);

            if (opCurso.isPresent() == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }

            return new ResponseEntity<>(opCurso.get().getAulas(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* 
    @GetMapping
    public ResponseEntity<List<Aulas>> getAll() {
        try {
            List<Aulas> items = new ArrayList<Aulas>();

            aulaService.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

@GetMapping("{id}")
    public ResponseEntity<Aulas> getById(@PathVariable("id") long id) {
        Optional<Aulas> existingItemOptional = aulaService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


/* 
    @GetMapping("{id}")
    public ResponseEntity<Aulas> getById(@PathVariable("id") long id) {
        Optional<Aulas> existingItemOptional = aulaService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 */   
    /* 
    @PostMapping("{idCurso}")
    public ResponseEntity<Aulas> create(@PathVariable("idCurso") long idCurso, @Valid @RequestBody Aulas item) throws AulaException {
        
        Aulas savedItem = aulaService.save(idCurso,item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
*/


@PostMapping
    public ResponseEntity<Aulas> create(@PathVariable("idCurso") long idAula, @RequestBody Aulas item) throws AulaException {
        Aulas savedItem = aulaService.save(idAula, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }


/* 
    @PostMapping
    public ResponseEntity<Aulas> create(@PathVariable("idCurso") long idPost, @RequestBody Aulas item) throws AulaException {
        Aulas savedItem = aulaService.save(idPost, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
*/
    @PutMapping("{id}")
    public ResponseEntity<Aulas> update(@PathVariable("id") long id, @Valid @RequestBody Aulas item) throws AulaException {
        
        return new ResponseEntity<>(aulaService.update(id, item), HttpStatus.OK);
        
}

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws AulaException {
        
        aulaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    
    }
}
