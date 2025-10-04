package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Modulo;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    @Query("SELECT m FROM Modulo m WHERE m.trilha.idTrilha = :idTrilha")
    List<Modulo> findByIdTrilha(Long idTrilha);
}
