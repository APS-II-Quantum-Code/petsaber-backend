package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.ProgressoExercicio;
import quantum_code.petsaber.repository.ProgressoExercicioRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressoExercicioService {

    private final ProgressoExercicioRepository progressoExercicioRepository;


    public ProgressoExercicio salvarProgressoExercicio(ProgressoExercicio progressoExercicio) {
        return progressoExercicioRepository.save(progressoExercicio);
    }

    public Optional<ProgressoExercicio> buscarProgressoExercicio(Long idProgressoModulo, Long idExercicio) {
        return progressoExercicioRepository.buscarProgressoExercicio(idProgressoModulo, idExercicio);
    }

    public Long contarExerciciosCorretosDoModulo(Long idProgressoModulo) {
        return progressoExercicioRepository.contarExerciciosCorretosDoModulo(idProgressoModulo);
    }

    public Integer buscarPontuacao(Long idTutor) {
        return progressoExercicioRepository.buscarPontuacao(idTutor);
    }
}
