package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Modulo;
import quantum_code.petsaber.domain.ProgressoModulo;
import quantum_code.petsaber.domain.ProgressoTrilha;
import quantum_code.petsaber.enuns.Status;
import quantum_code.petsaber.repository.ProgressoModuloRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressoModuloService {

    private final ProgressoModuloRepository progressoModuloRepository;


    public ProgressoModulo buscarOuCriarModulo(Long idTutor, Long idModulo, ProgressoTrilha progressoTrilha) {

        Optional<ProgressoModulo> progressoModulo = progressoModuloRepository.buscarProgressoModuloPorIdTutorEIdModulo(idTutor, idModulo);

        return progressoModulo.orElseGet(() -> progressoModuloRepository.save(ProgressoModulo.builder()
                .dataInicio(LocalDateTime.now())
                .status(Status.EM_ANDAMENTO)
                .progressoTrilha(progressoTrilha)
                .modulo(Modulo.builder().idModulo(idModulo).build())
                .build()));
    }

    public List<ProgressoModulo> buscarProgressoPorIdTutorEIdTrilha(Long idTutor, Long idTrilha) {

        return progressoModuloRepository.buscarProgressoPorIdTutorEIdTrilha(idTutor, idTrilha);
    }

    public void inicializarModulosDaTrilha(ProgressoTrilha progressoTrilha, List<Modulo> modulos) {
        modulos.forEach(modulo -> progressoModuloRepository.save(ProgressoModulo.builder()
                .status(Status.NAO_INICIADO)
                .progressoTrilha(progressoTrilha)
                .modulo(modulo)
                .build()));
    }

    public ProgressoModulo buscarProgressoModuloPorIdTutorEIdModulo(Long idTutor, Long idModulo) {
        return progressoModuloRepository.buscarProgressoModuloPorIdTutorEIdModulo(idTutor, idModulo)
                .orElseThrow(() -> new RuntimeException("Dado não encontrado"));
    }

    public void verificarEAtualizarStatusModulo(ProgressoModulo progressoModulo, Long exerciciosCorretosCount, Long totalExercicios) {
        // Se o módulo ainda não foi iniciado, marca como EM_ANDAMENTO
        if (progressoModulo.getStatus() == Status.NAO_INICIADO) {
            progressoModulo.setStatus(Status.EM_ANDAMENTO);
            progressoModulo.setDataInicio(java.time.LocalDateTime.now());
        }

        // Se todos os exercícios foram respondidos corretamente, marca como CONCLUIDO
        if (exerciciosCorretosCount.equals(totalExercicios)) {
            progressoModulo.setStatus(Status.CONCLUIDO);
            progressoModulo.setDateConclusao(java.time.LocalDateTime.now());
        }

        progressoModuloRepository.save(progressoModulo);
    }

    public Integer contarModulosConcluidos(Long idProgressoTrilha) {
        return progressoModuloRepository.contarModulosConcluidos(idProgressoTrilha);
    }

    public Integer contarTotalModulos(Long idProgressoTrilha) {
        return progressoModuloRepository.contarTotalModulos(idProgressoTrilha);

    }

    public Integer contarQtdModulosConcluidas(Long idTutor) {
        return progressoModuloRepository.contarQtdModulosConcluidas(idTutor);
    }
}
