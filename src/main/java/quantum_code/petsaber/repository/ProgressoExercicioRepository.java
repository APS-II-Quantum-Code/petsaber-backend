package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.ProgressoExercicio;

import java.util.Optional;

public interface ProgressoExercicioRepository extends JpaRepository<ProgressoExercicio, Long> {

    @Query("SELECT p FROM ProgressoExercicio p WHERE p.progressoModulo.idProgressoModulo = :idProgressoModulo AND p.exercicio.idExercicio = :idExercicio")
    Optional<ProgressoExercicio> buscarProgressoExercicio(Long idProgressoModulo, Long idExercicio);

    @Query("SELECT COUNT(p) FROM ProgressoExercicio p WHERE p.progressoModulo.idProgressoModulo = :idProgressoModulo AND p.correta = true")
    Long contarExerciciosCorretosDoModulo(Long idProgressoModulo);

    @Query("SELECT SUM(p.pontosObtidos) FROM ProgressoExercicio p WHERE p.progressoModulo.progressoTrilha.tutor.idTutor = :idTutor")
    Integer buscarPontuacao(Long idTutor);
}
