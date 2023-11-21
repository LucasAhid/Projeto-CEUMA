package br.com.api.cursos.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.cursos.interfaces.IAluno;
import br.com.api.cursos.interfaces.ICurso;
import br.com.api.cursos.interfaces.IResposta;
import br.com.api.cursos.repositorios.AlunoRepositorio;

@Service
public class AlunoServico {
    
    @Autowired
    private AlunoRepositorio alunoRepositorio;
    @Autowired
    private IResposta resp;

    public void removerAluno(Long id) {
        alunoRepositorio.deleteById(id);
    }

    public IAluno alterarAluno(Long id, IAluno alunoAtualizado) {
        if(id != null){         
            return alunoRepositorio.save(alunoAtualizado);
        }
        return alunoAtualizado;
    }
    
    public ResponseEntity<?> cadastrarAluno(IAluno aluno) {
        return new ResponseEntity<IAluno>(alunoRepositorio.save(aluno), HttpStatus.CREATED);
    }

    public List<IAluno> listarAlunosPorCurso(ICurso curso) {
        return alunoRepositorio.findByCurso(curso);
    }
}
