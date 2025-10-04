package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Exercicio;
import quantum_code.petsaber.repository.ExercicioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public Exercicio criarExercicio(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public List<Exercicio> buscarExerciciosPorIdModulo(Long idModulo) {
        return exercicioRepository.buscarExerciciosPorIdModulo(idModulo);
    }
}
