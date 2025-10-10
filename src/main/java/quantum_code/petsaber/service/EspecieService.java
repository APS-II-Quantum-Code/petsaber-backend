package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Especie;
import quantum_code.petsaber.dto.EspecieResponseDto;
import quantum_code.petsaber.repository.EspecieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecieService {

    private final EspecieRepository especieRepository;

    public List<Especie> buscarEspecies() {
        return especieRepository.findAll();
    }
}
