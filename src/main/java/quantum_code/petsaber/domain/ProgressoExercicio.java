package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PROGRESSO_EXERCICIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProgressoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgressoExericicio;
    private LocalDateTime dataTentativa;
    private Boolean correta;
    private Double pontosObtidos;

    @ManyToOne
    @JoinColumn(name = "ID_PROGRESSO_MODULO")
    private ProgressoModulo progressoModulo;

    @ManyToOne
    @JoinColumn(name = "ID_EXERCICIO")
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "ID_ALTERNATIVA_ESCOLHIDA")
    private Alternativa alternativaEscolhida;

}
