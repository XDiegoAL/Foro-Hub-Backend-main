package alura.forohub.api.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Page<Topico> findAllByOrderByFechaCreacionAsc(Pageable pageable);
    Page<Topico> findByCursoNombre(String nombreCurso, Pageable pageable);
    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :year")
    Page<Topico> findByYear(@Param("year") int year, Pageable pageable);
}
