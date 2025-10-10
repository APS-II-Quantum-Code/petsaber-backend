package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Raca;

import java.util.List;

public interface RacaRepository extends JpaRepository<Raca, Long> {

    @Query("SELECT r FROM Raca r WHERE r.especie.idEspecie = :idEspecie")
    List<Raca> buscarRacaPorEspecieId(Long idEspecie);
}
