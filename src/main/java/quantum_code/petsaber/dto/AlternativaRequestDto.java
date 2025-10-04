package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlternativaRequestDto {

    private String conteudo;
    private Boolean correta;
}
