package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlternativaResponseDto {

    private Long idAlternativa;
    private String conteudo;
}
