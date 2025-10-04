package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrilhaResponseDto {

    private Long idTrilha;
    private String nome;
    private String descricao;
    private String nivel;
    private String raca;
}
