package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Porte;
import quantum_code.petsaber.repository.PorteRepository;

@Service
@RequiredArgsConstructor
public class PorteService {

    private final PorteRepository porteRepository;

    public Porte buscarPortePorId(Long idPorte) {
        return porteRepository.findById(idPorte).orElseThrow(() -> new RuntimeException("Porte não encontrada"));
    }
}
