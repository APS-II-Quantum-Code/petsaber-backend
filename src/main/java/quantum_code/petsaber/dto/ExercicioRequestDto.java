package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import quantum_code.petsaber.domain.Alternativa;

import java.util.List;

@Data
@Builder
public class ExercicioRequestDto {

    private String nome;
    private String descricao;
    private Double pontuacao;
    private List<AlternativaRequestDto> alternativas;

}
