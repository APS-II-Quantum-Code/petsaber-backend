package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.ProgressoTrilha;
import quantum_code.petsaber.domain.Trilha;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.enuns.Status;
import quantum_code.petsaber.repository.ProgressoTrilhaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressoTrilhaService {

    private final ProgressoTrilhaRepository progressoTrilhaRepository;

    public ProgressoTrilha buscarOuCriarTrilha(Tutor tutor, Trilha trilha) {

        Optional<ProgressoTrilha> progressoTrilha = progressoTrilhaRepository.buscarProgressoTrilhaPorIdTutorEIdTrilha(tutor.getIdTutor(), trilha.getIdTrilha());

        return progressoTrilha.orElseGet(() -> progressoTrilhaRepository.saveAndFlush(ProgressoTrilha.builder()
                .trilha(trilha)
                .tutor(tutor)
                .dataInicio(LocalDateTime.now())
                .status(Status.EM_ANDAMENTO)
                .modulosConcluidos(0)
                .build()));

    }

    public Page<ProgressoTrilha> buscarProgressoTrilhasPorIdTutor(Pageable pageable, Long idTutor) {
        return progressoTrilhaRepository.buscarProgressoTrilhasPorIdTutor(pageable, idTutor);
    }

    public void verificarEAtualizarStatusTrilha(ProgressoTrilha progressoTrilha, Integer modulosConcluidos, Integer totalModulos) {

        progressoTrilha.setModulosConcluidos(modulosConcluidos);

        // Se pelo menos um módulo foi iniciado/concluído e a trilha estava NAO_INICIADO, muda para EM_ANDAMENTO
        if (progressoTrilha.getStatus() == Status.NAO_INICIADO && modulosConcluidos > 0) {
            progressoTrilha.setStatus(Status.EM_ANDAMENTO);
            if (progressoTrilha.getDataInicio() == null) {
                progressoTrilha.setDataInicio(LocalDateTime.now());
            }
        }

        // Se todos os módulos foram concluídos, marca a trilha como CONCLUIDA
        if (modulosConcluidos.equals(totalModulos) && totalModulos > 0) {
            progressoTrilha.setStatus(Status.CONCLUIDO);
            progressoTrilha.setDataConclusao(LocalDateTime.now());
        }

        progressoTrilhaRepository.save(progressoTrilha);
    }

    public ProgressoTrilha buscarProgressoTrilhaPorId(Long idProgressoTrilha) {
        return progressoTrilhaRepository.findById(idProgressoTrilha)
                .orElseThrow(() -> new RuntimeException("Progresso da trilha não encontrado"));
    }

    public Integer contarQtdTrilhasConcluidas(Long idTutor) {
        return progressoTrilhaRepository.contarQtdTrilhasConcluidas(idTutor);
    }
}
