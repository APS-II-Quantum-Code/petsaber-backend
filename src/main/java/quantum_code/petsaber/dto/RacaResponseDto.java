package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RacaResponseDto {

    private Long idRaca;
    private String nome;
}
