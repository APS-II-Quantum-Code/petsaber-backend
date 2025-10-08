package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Trilha;

import java.util.Optional;

public interface TrilhaRepository extends JpaRepository<Trilha, Long> {

    @Query("SELECT t FROM Trilha t JOIN FETCH t.modulos m WHERE m.idModulo = :idModulo")
    Optional<Trilha> buscarTrilhaPorModuloId(Long idModulo);
}
