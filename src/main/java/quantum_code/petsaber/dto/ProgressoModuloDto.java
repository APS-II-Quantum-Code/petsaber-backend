package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProgressoModuloDto {

    private Long idModulo;
    private String nomeModulo;
    private String status;
    private Double percentualAcerto;
}
