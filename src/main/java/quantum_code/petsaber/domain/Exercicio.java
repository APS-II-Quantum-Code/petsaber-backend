package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_EXERCICIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExercicio;
    private String nome;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private Double pontuacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MODULO")
    private Modulo modulo;

    @OneToMany(mappedBy = "exercicio")
    private List<Alternativa> alternativas;
}
