package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Recompensa;
import quantum_code.petsaber.repository.RecompensaRepository;

@Service
@RequiredArgsConstructor
public class RecompensaService {

    private final RecompensaRepository repository;

    public Page<Recompensa> buscarRecompensas(Pageable pageable) {
        return repository.buscarRecompensas(pageable);
    }
}
