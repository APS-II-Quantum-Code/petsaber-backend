package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Modulo;
import quantum_code.petsaber.dto.ModuloRequestDto;
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

    public void editarModulo(Long idModulo, ModuloRequestDto moduloRequestDto) {
        Modulo modulo = buscarModuloPorId(idModulo);
        modulo.setNome(moduloRequestDto.getNome());
        modulo.setDescricao(moduloRequestDto.getDescricao());
        modulo.setDuracaoHoras(modulo.getDuracaoHoras());
        modulo.setConteudo(moduloRequestDto.getConteudo());

        moduloRepository.save(modulo);
    }

    public void deletarModulo(Long idModulo) {
        Modulo modulo = buscarModuloPorId(idModulo);
        modulo.setAtivo(Boolean.FALSE);
        moduloRepository.save(modulo);
    }
}
