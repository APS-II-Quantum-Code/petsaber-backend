package quantum_code.petsaber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecompensaDto {

    private Long idRecompensa;
    private String titulo;
    private String descricao;
    private Double pontuacaoMinima;

}
