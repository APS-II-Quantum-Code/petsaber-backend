package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Recompensa;
import quantum_code.petsaber.domain.RecompensaTutor;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.repository.RecompensaTutorRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RecompensaTutorService {

    private final RecompensaTutorRepository recompensaTutorRepository;

    public void resgatarRecompensa(Recompensa recompensa, Tutor tutor) {
        recompensaTutorRepository.save(RecompensaTutor.builder()
                        .recompensa(recompensa)
                        .tutor(tutor)
                        .dataResgate(LocalDate.now())
                .build());
    }
}
