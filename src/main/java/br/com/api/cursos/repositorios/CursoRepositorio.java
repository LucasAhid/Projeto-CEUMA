package br.com.api.cursos.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.cursos.interfaces.ICurso;

@Repository
public interface CursoRepositorio extends JpaRepository<ICurso, Long> {

    Optional<ICurso> findById(Long cursoId);

    List<ICurso> findAll();

    ICurso save(ICurso curso);

    void deleteById(Long id);
}
