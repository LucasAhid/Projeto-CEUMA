package br.com.api.cursos.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public void removerCurso(Long id) {
        cursoRepositorio.deleteById(id);
    }

    public ResponseEntity<?> alterarCurso(Long id, ICurso cursoAtualizado) {
         if(cursoAtualizado.getCodigo().equals("")){
            resp.setMensagem("O CODIGO do curso é obrigatório");            
            return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
        }
            else if(cursoAtualizado.getNome().equals("")){
                resp.setMensagem("O NOME do curso é obrigatório");            
                return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
            }
                else if(cursoAtualizado.getCargaHoraria() == null){
                    resp.setMensagem("A CARGA HORÁRIA do curso é obrigatório");            
                    return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }   
                    else{
                         return new ResponseEntity<ICurso>(cursoRepositorio.save(cursoAtualizado), HttpStatus.OK);
                    }
    }
    public ResponseEntity<ICurso> buscarCursoPorId(@PathVariable Long id) {
        return  cursoRepositorio.buscarCursoPorId(id);
    }
}
