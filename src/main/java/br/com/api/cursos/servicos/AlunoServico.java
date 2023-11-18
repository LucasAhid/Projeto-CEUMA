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
    
    public  ResponseEntity<?> cadastrarAluno(IAluno aluno) {
            if(aluno.getCep().equals("")){
                resp.setMensagem("Por favor preencha o seu CEP");            
                return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
            }
                else if(aluno.getNome().equals("")){
                   resp.setMensagem("Por favor preencha o seu NOME");           
                    return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }
                else if(aluno.getCodigo().equals("")){
                     resp.setMensagem("Por favor preencha o seu CÓDIGO");           
                     return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }
                else if(aluno.getCpf().equals("")){
                     resp.setMensagem("Por favor preencha o seu CPF");           
                     return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }
                else if(aluno.getEmail().equals("")){
                     resp.setMensagem("Por favor preencha o seu EMAIL");           
                     return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }
                else if(aluno.getEndereco().equals("")){
                     resp.setMensagem("Por favor preencha o seu ENDEREÇO");           
                     return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }
                else if(aluno.getTelefone().equals("")){
                      resp.setMensagem("Por favor preencha o seu TELEFONE");           
                      return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }
                else if(aluno.getCurso().getNome().equals("")){
                      resp.setMensagem("Por favor insira pelo menos 1 CURSO");           
                      return new ResponseEntity<IResposta>(resp, HttpStatus.BAD_REQUEST);
                }  
                else{
                    return new ResponseEntity<IAluno>(alunoRepositorio.save(aluno), HttpStatus.CREATED);
                }
    }

    public List<IAluno> listarAlunosPorCurso(ICurso curso) {
        //return null;
        return alunoRepositorio.findByCurso(curso);
    }
}
