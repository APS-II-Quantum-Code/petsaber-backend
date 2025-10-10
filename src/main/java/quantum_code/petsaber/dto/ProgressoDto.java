package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProgressoDto {

    private Integer qtdPets;
    private Integer qtdTrilhasConcluidas;
    private Integer pontosTotais;
    private Integer qtdModulosConcluidos;
}
