package br.com.api.cursos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.cursos.interfaces.IAluno;
import br.com.api.cursos.interfaces.ICurso;

public interface AlunoRepositorio extends JpaRepository<IAluno, Long> {

    List<IAluno> findByCurso(ICurso curso);
    
    IAluno save(IAluno aluno);
    
}