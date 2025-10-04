package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import quantum_code.petsaber.enuns.Nivel;

import java.util.List;

@Data
@Builder
public class TrilhaRequestDto {

    private String nome;
    private String descricao;
    private Nivel nivel;
    private Double duracaoHoras;
    private Long idRaca;
}
