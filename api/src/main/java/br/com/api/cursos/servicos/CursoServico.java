package br.com.api.cursos.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.cursos.interfaces.ICurso;
import br.com.api.cursos.interfaces.IResposta;
import br.com.api.cursos.repositorios.CursoRepositorio;

@Service
public class CursoServico {
    @Autowired
    private CursoRepositorio cursoRepositorio;
    @Autowired
    private IResposta resp;

    public Iterable<ICurso> listarCursos() {
        return cursoRepositorio.findAll();
    }
    
    public ResponseEntity<?> cadastrarCurso(ICurso curso) {
        if(curso.getCodigo().equals("")){
            resp.setMensagem("O CODIGO do curso é obrigatório");            
            return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
        }
            else if(curso.getNome().equals("")){
                resp.setMensagem("O NOME do curso é obrigatório");            
                return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
            }
                else if(curso.getCargaHoraria() == null){
                    resp.setMensagem("A CARGA HORÁRIA do curso é obrigatório");            
                    return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }   
                    else{
                         return new ResponseEntity<ICurso>(cursoRepositorio.save(curso), HttpStatus.CREATED);
                    }
    }

    public ResponseEntity<IResposta> removerCurso(Long id) {
        Optional<ICurso> cursoExistente = cursoRepositorio.findById(id);
        if (cursoExistente.isPresent()) {
            cursoRepositorio.deleteById(id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } else {
            resp.setMensagem("Curso não encontrado");
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
    }
    

    public ResponseEntity<?> alterarCurso(Long id, ICurso cursoAtualizado) {
        if (cursoAtualizado.getCodigo().equals("")) {
            resp.setMensagem("O CODIGO do curso é obrigatório");
            return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
        } else if (cursoAtualizado.getNome().equals("")) {
            resp.setMensagem("O NOME do curso é obrigatório");
            return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
        } else if (cursoAtualizado.getCargaHoraria() == null) {
            resp.setMensagem("A CARGA HORÁRIA do curso é obrigatório");
            return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
        } else {
            cursoAtualizado.setId(id); // Certifique-se de que o ID está definido
            return new ResponseEntity<>(cursoRepositorio.save(cursoAtualizado), HttpStatus.OK);
        }
    }
        
    
    public Optional<ICurso> buscarCursoPorId(Long id) {
        return cursoRepositorio.findById(id);
    }
    

}
