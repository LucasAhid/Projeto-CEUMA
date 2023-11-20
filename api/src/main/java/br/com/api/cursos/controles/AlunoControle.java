package br.com.api.cursos.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.cursos.interfaces.IAluno;
import br.com.api.cursos.interfaces.ICurso;
import br.com.api.cursos.repositorios.CursoRepositorio;
import br.com.api.cursos.servicos.AlunoServico;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/alunos")
public class AlunoControle {

    @Autowired
    private AlunoServico alunoServico;
    @Autowired
    private CursoRepositorio cursoRepositorio;


    @GetMapping("/por-curso/{cursoId}")
    public ResponseEntity<List<IAluno>> listarAlunosPorCurso(@PathVariable Long cursoId) {
        Optional<ICurso> cursoOptional = cursoRepositorio.findById(cursoId);

        if (cursoOptional.isPresent()) {
            ICurso curso = cursoOptional.get();

            List<IAluno> alunos = alunoServico.listarAlunosPorCurso(curso);

            return ResponseEntity.ok(alunos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarAluno(@RequestBody IAluno aluno) {
        ResponseEntity<?> novoAluno = alunoServico.cadastrarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        alunoServico.removerAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<IAluno> alterarAluno(@PathVariable Long id, @RequestBody IAluno alunoAtualizado) {
        IAluno aluno = alunoServico.alterarAluno(id, alunoAtualizado);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
