package com.ap1.crudap1.service;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ap1.crudap1.repository.CursoRepository;
import com.ap1.crudap1.exception.BusinessException;
import com.ap1.crudap1.exception.CursoException;
import com.ap1.crudap1.model.Curso;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AzureStoreAccountSevice azureStoreAccountSevice;

    
    public Curso create(Curso curso){
        return this.cursoRepository.save(curso);
    } 
    
    public Optional<Curso> getById(long id){
        return this.cursoRepository.findById(id);
    }

    public java.util.List<Curso> getAll(){
        return this.cursoRepository.findAll();
    }


    public void saveOrUpdate(Curso item){
        this.cursoRepository.save(item);
    }


    public Curso update(long id, Curso newCurso) throws CursoException{
        Optional<Curso> opCurso = this.cursoRepository.findById(id);

        if (opCurso.isPresent() == false){
            throw new CursoException("Nao encontrei o curso a ser atualizado");
        }
        Curso curso = opCurso.get();
        curso.setNome(newCurso.getNome());
        curso.setCategoria(newCurso.getCategoria());

        this.cursoRepository.save(curso);

        return curso;

    }

    public void delete(long id) throws CursoException{
        Optional<Curso> opCurso = this.cursoRepository.findById(id);

        if (opCurso.isPresent() == false){
            throw new CursoException("Nao encontrei o curso a ser excluido");
        }

        this.cursoRepository.delete(opCurso.get());


    }

    public void uploadFileToCurso(MultipartFile file, long id) throws CursoException, Exception{
        Optional<Curso> opCurso = this.cursoRepository.findById(id);
            
        if (opCurso.isPresent() == false){
            throw new CursoException("Não encontrei o curso para associar a imagem");
        }

        Curso curso = opCurso.get();

        String urlImage = this.azureStoreAccountSevice.uploadFileToAzure(file);
        curso.setUrlImage(urlImage);
        this.cursoRepository.save(curso);

    }

}
