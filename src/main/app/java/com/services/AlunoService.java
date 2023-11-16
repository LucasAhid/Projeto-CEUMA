package com.services;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<aluno> listarAlunosPorCurso(Curso curso) {
        return alunoRepository.findByCurso(curso);
    }

    public aluno cadastrarAluno(aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void removerAluno(Long id) {
        alunoRepository.deleteById(id);
    }

    public aluno alterarAluno(Long id, aluno alunoAtualizado) {
        // Implemente a lógica para atualizar um aluno existente
    }
}
