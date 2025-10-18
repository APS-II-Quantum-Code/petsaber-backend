package quantum_code.petsaber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRankingDto {
    private Long idTutor;
    private String nome;
    private Double pontuacao;
    private Boolean logado;
}
