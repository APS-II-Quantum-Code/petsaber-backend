package quantum_code.petsaber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.ProgressoTrilha;

import java.util.Optional;

public interface ProgressoTrilhaRepository extends JpaRepository<ProgressoTrilha, Long> {

    @Query("SELECT p FROM ProgressoTrilha p WHERE p.tutor.idTutor = :idTutor AND p.trilha.idTrilha = :idTrilha")
    Optional<ProgressoTrilha> buscarProgressoTrilhaPorIdTutorEIdTrilha(Long idTutor, Long idTrilha);

    @Query("SELECT p FROM ProgressoTrilha p " +
            "JOIN FETCH p.trilha " +
            "WHERE p.tutor.idTutor = :idTutor")
    Page<ProgressoTrilha> buscarProgressoTrilhasPorIdTutor(Pageable pageable, Long idTutor);

    @Query("SELECT p FROM ProgressoTrilha p " +
            "WHERE p.trilha.idTrilha = :idTrilha AND p.tutor.idTutor = :idTutor")
    Optional<ProgressoTrilha> verificarSeTutorIniciouTrilha(Long idTutor, Long idTrilha);

    @Query("SELECT COUNT(p) FROM ProgressoTrilha p WHERE p.status = 'CONCLUIDO' AND p.tutor.idTutor = :idTutor")
    Integer contarQtdTrilhasConcluidas(Long idTutor);
}
