package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Alternativa;

import java.util.List;

public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

    @Query("SELECT a FROM Alternativa a WHERE a.exercicio.idExercicio = :idExercicio " +
            "AND a.ativa = true")
    List<Alternativa> buscarAlternativasPorExercicioId(Long idExercicio);
}
