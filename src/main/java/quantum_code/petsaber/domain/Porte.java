package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PORTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Porte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPorte;
    private String nome;
    private String descricao;
}
