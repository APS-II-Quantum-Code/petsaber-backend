package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import quantum_code.petsaber.enuns.Status;

@Data
@Builder
public class ProgressoModuloDto {

    private Long idModulo;
    private String nomeModulo;
    private Status status;
}
