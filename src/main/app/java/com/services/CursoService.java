package com.services;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso cadastrarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void removerCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso alterarCurso(Long id, Curso cursoAtualizado) {
        // Implemente a lógica para atualizar um curso existente
    }
}
