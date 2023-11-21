package br.com.api.cursos.controles;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.cursos.interfaces.ICurso;
import br.com.api.cursos.servicos.CursoServico;



@CrossOrigin
@RestController
@RequestMapping("/api/cursos")
public class CursoControle {

     @GetMapping
    public String teste() {
        return "API CURSOS";
    }
    
    @Autowired
    private CursoServico cursoServico;

    @GetMapping("/listarCursos")
    public Iterable<ICurso> listarCursos() {
        return cursoServico.listarCursos();
    }

    @PostMapping("/cadastrarCurso")
    public ResponseEntity<?> cadastrarCurso(@RequestBody ICurso curso) {
        return cursoServico.cadastrarCurso(curso);
    }

    @DeleteMapping("/{id}")
    public void removerCurso(@PathVariable Long id) {
        cursoServico.removerCurso(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarCurso(@PathVariable Long id, @RequestBody ICurso cursoAtualizado) {
        return cursoServico.alterarCurso(id, cursoAtualizado);
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ICurso>> buscarCursoPorId(@PathVariable Long id) {
        Optional<ICurso> curso = cursoServico.buscarCursoPorId(id);
        return ResponseEntity.ok(curso);
     }

     @RequestMapping(value = "/api/cursos/{id}", method = RequestMethod.OPTIONS)
        public ResponseEntity<Void> options() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


