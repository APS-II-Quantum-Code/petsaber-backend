package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Alternativa;
import quantum_code.petsaber.domain.Exercicio;
import quantum_code.petsaber.dto.AlternativaRequestDto;
import quantum_code.petsaber.repository.AlternativaRepository;

import java.util.List;

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

    public List<Alternativa> buscarAlternativasPorExercicioId(Long idExercicio) {
        return alternativaRepository.buscarAlternativasPorExercicioId(idExercicio);
    }

    public void editarAlternativa(Long idAlternativa, AlternativaRequestDto alternativaRequestDto) {
        Alternativa alternativa = buscarAlternativaPorId(idAlternativa);

        alternativa.setConteudo(alternativaRequestDto.getConteudo());
        alternativa.setCorreta(alternativaRequestDto.getCorreta());

        alternativaRepository.save(alternativa);
    }

    public void deletarAlternativa(Long idAlternativa) {
        Alternativa alternativa = buscarAlternativaPorId(idAlternativa);

        alternativa.setAtiva(false);

        alternativaRepository.save(alternativa);
    }

    public void criarAlternativa(Exercicio exercicio, AlternativaRequestDto alternativaRequestDto) {
        Alternativa alternativa = new Alternativa();
        alternativa.setConteudo(alternativaRequestDto.getConteudo());
        alternativa.setCorreta(alternativaRequestDto.getCorreta());
        alternativa.setAtiva(true);
        alternativa.setExercicio(exercicio);

        alternativaRepository.save(alternativa);
    }
}
