@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private cursoService cursoService;

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @PostMapping
    public Curso cadastrarCurso(@RequestBody Curso curso) {
        return cursoService.cadastrarCurso(curso);
    }

    @DeleteMapping("/{id}")
    public void removerCurso(@PathVariable Long id) {
        cursoService.removerCurso(id);
    }

    @PutMapping("/{id}")
    public Curso alterarCurso(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        return cursoService.alterarCurso(id, cursoAtualizado);
    }
}


