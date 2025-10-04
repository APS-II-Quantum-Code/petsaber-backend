package quantum_code.petsaber.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Exercicio;

import java.util.List;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

    @Query("SELECT e FROM Exercicio e " +
            "JOIN FETCH e.alternativas " +
            "WHERE e.modulo.idModulo = :idModulo")
    List<Exercicio> buscarExerciciosPorIdModulo(Long idModulo);
}
