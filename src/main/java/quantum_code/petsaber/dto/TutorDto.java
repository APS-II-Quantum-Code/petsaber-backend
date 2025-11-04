package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TutorDto {
    private Long idTutor;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private LocalDate dataNascimento;
    private String genero;
    private String nacionalidade;

}
