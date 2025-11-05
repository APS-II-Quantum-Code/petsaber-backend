package quantum_code.petsaber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Recompensa;

public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {

    @Query("SELECT r FROM Recompensa r " +
            "WHERE r.idRecompensa NOT IN (SELECT rt.recompensa.idRecompensa FROM RecompensaTutor rt WHERE rt.tutor.idTutor = :idTutor)" +
            "ORDER BY r.pontuacaoMinima ASC ")
    Page<Recompensa> buscarRecompensas(Pageable pageable, Long idTutor);

    @Query("SELECT r FROM Recompensa r " +
            "WHERE r.idRecompensa IN (SELECT rt.recompensa.idRecompensa FROM RecompensaTutor rt WHERE rt.tutor.idTutor = :idTutor)" )
    Page<Recompensa> buscarRecompensasPorIdTutor(Long idTutor, Pageable pageable);
}
