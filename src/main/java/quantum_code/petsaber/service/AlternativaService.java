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

    public Alternativa buscarAlternativaPorId(Long idAlternativaEscolhida) {

        return alternativaRepository.findById(idAlternativaEscolhida).
                orElseThrow(() -> new RuntimeException("Alternativa não encontada"));
    }
}
