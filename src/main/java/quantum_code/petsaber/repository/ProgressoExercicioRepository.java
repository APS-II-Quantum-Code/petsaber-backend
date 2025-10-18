package quantum_code.petsaber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.ProgressoExercicio;
import quantum_code.petsaber.dto.ItemRankingDto;

import java.util.Optional;

public interface ProgressoExercicioRepository extends JpaRepository<ProgressoExercicio, Long> {

    @Query("SELECT p FROM ProgressoExercicio p WHERE p.progressoModulo.idProgressoModulo = :idProgressoModulo AND p.exercicio.idExercicio = :idExercicio")
    Optional<ProgressoExercicio> buscarProgressoExercicio(Long idProgressoModulo, Long idExercicio);

    @Query("SELECT COUNT(p) FROM ProgressoExercicio p WHERE p.progressoModulo.idProgressoModulo = :idProgressoModulo AND p.correta = true")
    Long contarExerciciosCorretosDoModulo(Long idProgressoModulo);

    @Query("SELECT SUM(p.pontosObtidos) FROM ProgressoExercicio p WHERE p.progressoModulo.progressoTrilha.tutor.idTutor = :idTutor")
    Integer buscarPontuacao(Long idTutor);

    @Query("SELECT new quantum_code.petsaber.dto.ItemRankingDto(p.progressoModulo.progressoTrilha.tutor.idTutor, p.progressoModulo.progressoTrilha.tutor.nome, SUM(p.pontosObtidos), false)  " +
            "FROM ProgressoExercicio p " +
            "GROUP BY p.progressoModulo.progressoTrilha.tutor " +
            "ORDER BY SUM(p.pontosObtidos)")
    Page<ItemRankingDto> buscarRanking(Pageable pageable);
}
