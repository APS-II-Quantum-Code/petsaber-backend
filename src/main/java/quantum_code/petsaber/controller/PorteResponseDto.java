package quantum_code.petsaber.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PorteResponseDto {

    private Long idPorte;
    private String nome;
    private String descricao;
}
