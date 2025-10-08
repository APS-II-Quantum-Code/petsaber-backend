package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import quantum_code.petsaber.enuns.Status;

import java.time.LocalDateTime;

@Data
@Builder
public class ProgressoTrilhaDto {
    private Long idProgressoTrilha;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private Status status;
    private Double percentualConclusao;
    private TrilhaResponseDto trilha;
}
