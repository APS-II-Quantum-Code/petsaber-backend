package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuloResponseDto {

    private Long idModulo;
    private String nome;
    private String descricao;
    private Double duracaoHoras;
    private String conteudo;
}
