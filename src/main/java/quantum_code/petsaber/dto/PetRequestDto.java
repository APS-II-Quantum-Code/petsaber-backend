package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;
import quantum_code.petsaber.enuns.Sexo;

import java.time.LocalDate;

@Data
@Builder
public class PetRequestDto {

    //TODO: Spring Validation
    //TODO: IMAGEM DO CACHORRO
    private String nome;
    private Long idRaca;
    private LocalDate dataNascimento;
    private Long idPorte;
    private Sexo sexo;
}
