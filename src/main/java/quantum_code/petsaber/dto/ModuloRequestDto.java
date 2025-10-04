package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuloRequestDto {

    private String nome;
    private String descricao;
    private Double duracaoHoras;
    private String conteudo;

}
