package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.repository.TutorRepository;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    public Tutor buscarTutorPorId(Long idTutor) {
        return tutorRepository.findById(idTutor).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }
}
