package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Modulo;
import quantum_code.petsaber.dto.ModuloResponseDto;
import quantum_code.petsaber.repository.ModuloRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuloService {

    private final ModuloRepository moduloRepository;

    public void salvarModulos(List<Modulo> modulos) {
        moduloRepository.saveAll(modulos);
    }

    public Modulo salvarModulo(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    public Modulo buscarModuloPorId(Long idModulo) {
        return moduloRepository.findById(idModulo).orElseThrow(() -> new RuntimeException("Modulo não encontrado"));
    }

    public List<Modulo> buscarModulosPorIdTrilha(Long idTrilha) {
        return moduloRepository.findByIdTrilha(idTrilha);
    }
}
