package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TutorDto {
    private Long idTutor;
    private String nome;
    private String cpf;
    private String email;
    private String senha;

}
