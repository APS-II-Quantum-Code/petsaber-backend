package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Trilha;
import quantum_code.petsaber.enuns.Nivel;
import quantum_code.petsaber.repository.TrilhaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrilhaService {

    private final TrilhaRepository trilhaRepository;

    public Trilha salvarTrilha(Trilha trilha) {
        return trilhaRepository.save(trilha);
    }

    public Trilha buscarTrilhaPorId(Long idTrilha) {
        return trilhaRepository.findById(idTrilha).orElseThrow(() -> new RuntimeException("Erro ao buscar trilha"));
    }

    public List<Trilha> buscarTrilhas() {
        return trilhaRepository.findAll();
    }

    public Page<Trilha> buscarTrilhas(Pageable pageable, Long idTutor, Long idRaca, Nivel nivel) {
        return trilhaRepository.buscarTodasTrilhasDisponiveis(pageable, idTutor, idRaca, nivel);
    }

    public Trilha buscarTrilhaPorModuloId(Long idModulo) {
        return trilhaRepository.buscarTrilhaPorModuloId(idModulo).orElseThrow(() -> new RuntimeException("Erro ao buscar trilha"));
    }
}
