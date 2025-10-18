package quantum_code.petsaber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Recompensa;

public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {

    @Query("SELECT r FROM Recompensa r " +
            "ORDER BY r.pontuacaoMinima ASC")
    Page<Recompensa> buscarRecompensas(Pageable pageable);
}
