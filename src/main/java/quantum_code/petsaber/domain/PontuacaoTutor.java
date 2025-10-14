package quantum_code.petsaber.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PONTUACAO_TUTOR")
public class PontuacaoTutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pontuacao;

    @ManyToOne
    @JoinColumn(name = "ID_TUTOR")
    private Tutor tutor;

    //private Raca raca;
}
