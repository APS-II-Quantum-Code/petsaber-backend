package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.ProgressoModulo;

import java.util.List;
import java.util.Optional;

public interface ProgressoModuloRepository extends JpaRepository<ProgressoModulo, Long> {

    @Query("SELECT p FROM ProgressoModulo p WHERE p.progressoTrilha.tutor.idTutor = :idTutor AND p.modulo.idModulo = :idModulo")
    Optional<ProgressoModulo> buscarProgressoModuloPorIdTutorEIdModulo(Long idTutor, Long idModulo);


    @Query("SELECT p FROM ProgressoModulo p " +
            "JOIN FETCH p.modulo " +
            "WHERE p.progressoTrilha.tutor.idTutor = :idTutor AND p.progressoTrilha.trilha.idTrilha = :idTrilha")
    List<ProgressoModulo> buscarProgressoPorIdTutorEIdTrilha(Long idTutor, Long idTrilha);

    @Query("SELECT COUNT(p) FROM ProgressoModulo p WHERE p.progressoTrilha.idProgressoTrilha = :idProgressoTrilha AND p.status = 'CONCLUIDO'")
    Integer contarModulosConcluidos(Long idProgressoTrilha);

    @Query("SELECT COUNT(p) FROM ProgressoModulo p WHERE p.progressoTrilha.idProgressoTrilha = :idProgressoTrilha")
    Integer contarTotalModulos(Long idProgressoTrilha);

    @Query("SELECT COUNT(p) FROM  ProgressoModulo p WHERE p.status = 'CONCLUIDO' AND p.progressoTrilha.tutor.idTutor = :idTutor")
    Integer contarQtdModulosConcluidas(Long idTutor);

    @Query("SELECT p FROM ProgressoModulo p " +
            "WHERE p.modulo.idModulo = :idModulo AND p.progressoTrilha.tutor.idTutor = :idTutor")
    ProgressoModulo buscarProgressoModuloPorId(Long idModulo, Long idTutor);
}
