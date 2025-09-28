package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PetResponseDto {

    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private String porte;
    private String sexo;
    private String urlImagem;
}
