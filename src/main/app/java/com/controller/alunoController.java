package com.controller;

import java.util.List;
import java.util.Optional;

import com.Interface.Aluno;
import com.Interface.Curso;
import com.Interface.CursoRepository;
import com.services.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping("/por-curso/{cursoId}")
    public ResponseEntity<List<Aluno>> listarAlunosPorCurso(@PathVariable Long cursoId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();

            List<Aluno> alunos = alunoService.listarAlunosPorCurso(curso);

            return ResponseEntity.ok(alunos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.cadastrarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        alunoService.removerAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alterarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Aluno aluno = alunoService.alterarAluno(id, alunoAtualizado);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
