package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProgressoTrilhaDto {

    private Long idProgressoTrilha;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private String status;
    private Integer modulosConcluidos;
    private TrilhaResponseDto trilha;
}
