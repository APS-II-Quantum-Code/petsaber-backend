package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ALTERNATIVA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlternativa;
    private String conteudo;
    private Boolean correta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EXERCICIO")
    private Exercicio exercicio;

}
