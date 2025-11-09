package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Raca;
import quantum_code.petsaber.domain.Trilha;
import quantum_code.petsaber.dto.TrilhaRequestDto;
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
        return trilhaRepository.buscarTrilhaPorId(idTrilha).orElseThrow(() -> new RuntimeException("Erro ao buscar trilha"));
    }

    public List<Trilha> buscarTrilhas() {
        return trilhaRepository.buscarTrilhas();
    }

    public Page<Trilha> buscarTrilhas(Pageable pageable, Long idTutor, Long idRaca, Nivel nivel) {
        return trilhaRepository.buscarTodasTrilhasDisponiveis(pageable, idTutor, idRaca, nivel);
    }

    public Trilha buscarTrilhaPorModuloId(Long idModulo) {
        return trilhaRepository.buscarTrilhaPorModuloId(idModulo).orElseThrow(() -> new RuntimeException("Erro ao buscar trilha"));
    }

    public void editarTrilha(Long idTrilha, TrilhaRequestDto trilhaRequestDto, Raca raca) {

        Trilha trilha = buscarTrilhaPorId(idTrilha);
        trilha.setNome(trilhaRequestDto.getNome());
        trilha.setDescricao(trilhaRequestDto.getDescricao());
        trilha.setNivel(trilhaRequestDto.getNivel());
        trilha.setRaca(raca);

        trilhaRepository.save(trilha);
    }

    public void criarTrilha(TrilhaRequestDto trilhaRequestDto, Raca raca) {
        salvarTrilha(Trilha.builder()
                .nome(trilhaRequestDto.getNome())
                .descricao(trilhaRequestDto.getDescricao())
                .nivel(trilhaRequestDto.getNivel())
                .raca(raca)
                .ativo(true)
                .build());
    }

    public void deletarTrilha(Long idTrilha) {
        Trilha trilha = buscarTrilhaPorId(idTrilha);

        trilha.setAtivo(false);

        salvarTrilha(trilha);
    }
}
