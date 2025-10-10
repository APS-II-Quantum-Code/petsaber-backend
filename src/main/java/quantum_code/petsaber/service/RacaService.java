package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Raca;
import quantum_code.petsaber.repository.RacaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RacaService {

    private final RacaRepository racaRepository;

    public Raca buscarRacaPorId(Long idRaca) {
        return racaRepository.findById(idRaca).orElseThrow(() -> new RuntimeException("Raca não encontrada"));
    }

    public List<Raca> buscarRacaPorEspecieId(Long idEspecie) {
        return racaRepository.buscarRacaPorEspecieId(idEspecie);
    }
}
