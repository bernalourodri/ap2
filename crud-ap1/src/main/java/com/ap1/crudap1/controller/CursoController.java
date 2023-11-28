package com.ap1.crudap1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ap1.crudap1.exception.CursoException;
import com.ap1.crudap1.model.Curso;
import com.ap1.crudap1.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
@CrossOrigin
class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        try {
            List<Curso> items = new ArrayList<Curso>();

            cursoService.getAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Curso> getById(@PathVariable("id") long id) {
        Optional<Curso> existingItemOptional = cursoService.getById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Curso> create(@Valid @RequestBody Curso item) throws CursoException {
       
        Curso savedItem = cursoService.create(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        
    }

    @PutMapping("{id}")
    public ResponseEntity<Curso> update(@PathVariable("id") long id, @Valid @RequestBody Curso item) throws CursoException {
        
        return new ResponseEntity<>(cursoService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws CursoException {
     
        cursoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> uploadCursoImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) throws CursoException, Exception{
        
        cursoService.uploadFileToCurso(file, id);
        return new ResponseEntity<>(HttpStatus.OK);
        

    }
}