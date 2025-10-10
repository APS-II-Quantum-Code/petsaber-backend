package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Porte;
import quantum_code.petsaber.repository.PorteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PorteService {

    private final PorteRepository porteRepository;

    public Porte buscarPortePorId(Long idPorte) {
        return porteRepository.findById(idPorte).orElseThrow(() -> new RuntimeException("Porte não encontrada"));
    }

    public List<Porte> buscarTodosPortes() {
        return porteRepository.findAll();
    }
}
