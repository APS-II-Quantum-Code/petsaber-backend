package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExercicioResponseDto {

    private Long idExercicio;
    private String nome;
    private String descricao;
    private List<AlternativaResponseDto> alternativas;

}
