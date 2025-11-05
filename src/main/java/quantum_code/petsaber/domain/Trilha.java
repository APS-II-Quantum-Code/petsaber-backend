package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.*;
import quantum_code.petsaber.enuns.Nivel;

import java.util.List;

@Entity
@Table(name = "TB_TRILHA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trilha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrilha;
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Nivel nivel;
    private Integer horasTotais;
    private Integer modulosTotais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RACA")
    private Raca raca;

    @OneToMany(mappedBy = "trilha")
    private List<Modulo> modulos;

    private boolean ativo;
}
