package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import quantum_code.petsaber.controller.PorteResponseDto;
import quantum_code.petsaber.enuns.Sexo;

import java.time.LocalDate;

@Data
@Builder
public class PetResponseDto {

    private Long idPet;
    private String nome;
    private EspecieResponseDto especie;
    private RacaResponseDto raca;
    private LocalDate dataNascimento;
    private Integer idade;
    private PorteResponseDto porte;
    private String sexo;
    private String urlImagem;
}
