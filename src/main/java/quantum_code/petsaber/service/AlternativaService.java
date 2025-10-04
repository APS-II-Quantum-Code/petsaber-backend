package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Alternativa;
import quantum_code.petsaber.repository.AlternativaRepository;

@Service
@RequiredArgsConstructor
public class AlternativaService {

    private final AlternativaRepository alternativaRepository;

    public Alternativa salvarAlternativa(Alternativa alternativa) {
        return alternativaRepository.save(alternativa);
    }
}
