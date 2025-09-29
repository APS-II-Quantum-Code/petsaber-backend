package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import quantum_code.petsaber.enuns.Sexo;

import java.time.LocalDate;

@Data
@Builder
public class PetResponseDto {

    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private String porte;
    private Sexo sexo;
    private String urlImagem;
}
